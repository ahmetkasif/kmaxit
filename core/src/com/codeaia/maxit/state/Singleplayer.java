package com.codeaia.maxit.state;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.objects.Number;
import com.codeaia.maxit.objects.Player;
import com.codeaia.maxit.ui.Button;
import com.codeaia.maxit.ui.Text;

public class Singleplayer extends State {
	private Sprite bg;
	private ShapeRenderer sr;
	private Player player1, player2;
	private Number[][] maxitmap = new Number[5][5];
	private int spointx, spointy;
	private Text p1Score, p2Score;
	private Button menu;

	public Singleplayer(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		bg = new Sprite(new Texture(Gdx.files.internal("img/play/playbg.png")));
		sr = new ShapeRenderer();

		player1 = new Player("You", true);
		player2 = new Player("Computer (Easy)", false);
		
		p1Score = new Text(player1.getName() + ": " + player1.getScore(), 50, Gdx.graphics.getHeight() * 3 / 4);
		p2Score = new Text(player2.getName() + ": " + player2.getScore(), Gdx.graphics.getWidth() - 50 - (player2.getName().length() + 5) * 8, Gdx.graphics.getHeight() * 3 / 4);

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k] = new Number(((int) Game.width / 2) - (64 * 2)
						+ i * 74 - 10, k * 74 + 200);
			}
		}
		
		menu = new Button("Menu", Game.width / 64, Game.height * 1/ 16, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);

		spointx = (new Random()).nextInt(5);
		spointy = (new Random()).nextInt(5);

		maxitmap[spointx][spointy].setState(3);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Game.menu = new Menu(1);
			Game.state = 1;
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
		}
		
		if(menu.isClicked(mX, mY)){
			Game.menu = new Menu(1);
			Game.state = 1;
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.help != null) {
				Game.help.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			menu.playSound();
		}
		
		p1Score.setText(player1.getName() + ": " + player1.getScore());
		p2Score.setText(player2.getName() + ": " + player2.getScore());

		markSelectable(spointx, spointy);

		if (player1.getTurn() && !player2.getTurn()) {
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < 5; k++) {
					if (maxitmap[i][k].getState() == 1) {
						if (maxitmap[i][k].inbound(mX, mY)) {
							maxitmap[i][k].setState(2);
							if (Gdx.input.isTouched()) {
								undoSelectable(spointx, spointy);
								maxitmap[i][k].setState(3);

								spointx = i;
								spointy = k;

								player1.addScore(maxitmap[i][k].getNumber());
								player1.setTurn(false);
								player2.setTurn(true);
							}
						} else {
							maxitmap[i][k].setState(1);
						}
					}
				}
			}
		}

		if (!player1.getTurn() && player2.getTurn()) {
			greedyAI();
		}
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		
		batch.begin();
		bg.draw(batch);
		batch.end();
		
		p1Score.render(Color.WHITE);
		p2Score.render(Color.WHITE);
		
		sr.begin(ShapeType.Filled);
		sr.setColor(0.35f, 0.65f, 0.20f, 0.8f);
		sr.rect((Gdx.graphics.getWidth() - 400) / 2 - 10,
				(Gdx.graphics.getHeight() - 400) / 2 - 10, 420, 420);
		sr.setColor(0.15f, 0.55f, 0.7f, 0.7f);
		sr.rect((Gdx.graphics.getWidth() - 400) / 2,
				(Gdx.graphics.getHeight() - 400) / 2, 400, 400);
		sr.end();

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k].draw();
			}
		}
		menu.render(mX, mY);
	}

	public void markSelectable(int spointx, int spointy) {

		for (int i = 0; i < 5; i++) {
			maxitmap[i][spointy].setState(1);
		}

		for (int i = 0; i < 5; i++) {
			maxitmap[spointx][i].setState(1);
		}

		maxitmap[spointx][spointy].setState(3);
	}

	public void undoSelectable(int spointx, int spointy) {

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (maxitmap[i][k].getState() == 1
						|| maxitmap[i][k].getState() == 2) {
					maxitmap[i][k].setState(0);
				}
			}
		}
	}

	public void greedyAI() {
		int maxvalue = -25;
		int x = 0, y = 0;

		for (int i = spointx; i < 5; i++) {
			if (maxitmap[i][spointy].getNumber() > maxvalue
					&& maxitmap[i][spointy].getState() != 3) {
				maxvalue = maxitmap[i][spointy].getNumber();

				x = i;
				y = spointy;
			}
		}

		for (int i = 0; i < spointx; i++) {
			if (maxitmap[i][spointy].getNumber() > maxvalue
					&& maxitmap[i][spointy].getState() != 3) {
				maxvalue = maxitmap[i][spointy].getNumber();

				x = i;
				y = spointy;
			}
		}

		for (int k = spointy; k < 5; k++) {
			if (maxitmap[spointx][k].getNumber() > maxvalue
					&& maxitmap[spointx][k].getState() != 3) {
				maxvalue = maxitmap[spointx][k].getNumber();

				x = spointx;
				y = k;
			}
		}

		for (int k = 0; k < spointy; k++) {
			if (maxitmap[spointx][k].getNumber() > maxvalue
					&& maxitmap[spointx][k].getState() != 3) {
				maxvalue = maxitmap[spointx][k].getNumber();

				x = spointx;
				y = k;
			}
		}

		undoSelectable(spointx, spointy);
		maxitmap[x][y].setState(3);

		spointx = x;
		spointy = y;

		player2.addScore(maxitmap[x][y].getNumber());
		player2.setTurn(false);
		player1.setTurn(true);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
