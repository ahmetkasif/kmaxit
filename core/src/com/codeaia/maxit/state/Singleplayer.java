package com.codeaia.maxit.state;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.objects.Number;
import com.codeaia.maxit.objects.Player;
import com.codeaia.maxit.ui.Button;
import com.codeaia.maxit.ui.Text;

public class Singleplayer extends State {
	public static int mapSize;
	private Player player1, player2;
	private Number[][] maxitmap = new Number[7][7];
	private int spointx, spointy;
	private Text p1Score, p2Score;
	private Button menu, reset;
	private boolean turn;

	public Singleplayer(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		mapSize = 7;
		player1 = new Player("You");
		player2 = new Player("Computer (Easy)");

		turn = true;

		p1Score = new Text(player1.getName() + ": " + player1.getScore(), 50, Gdx.graphics.getHeight() * 3 / 4);
		p2Score = new Text(player2.getName() + ": " + player2.getScore(),
				Gdx.graphics.getWidth() - 50 - (player2.getName().length() + 5) * 8, Gdx.graphics.getHeight() * 3 / 4);

		for (int i = 0; i < mapSize; i++) {
			for (int k = 0; k < mapSize; k++) {
				maxitmap[i][k] = new Number((1280 - (72 * mapSize + 24)) / 2 + i * 72 + 16,
						(720 - (72 * mapSize + 24)) / 2 + k * 72 + 16);
			}
		}

		menu = new Button("Menu", 32, 45);
		reset = new Button("Reset", 32, 90);

		spointx = (new Random()).nextInt(5);
		spointy = (new Random()).nextInt(5);

		maxitmap[spointx][spointy].setState(3);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Game.menu.create();
			Game.state = 1;
		}

		if (menu.isClicked(mX, mY)) {
			Game.menu.create();
			Game.state = 1;
			menu.playSound();
		}
		
		if (reset.isClicked(mX, mY)) {
			Game.singleplayer.create();
			Game.state = 2;
			menu.playSound();
		}

		p1Score.setText(player1.getName() + ": " + player1.getScore());
		p1Score.setBackgroundColor(Color.DARK_GRAY);
		p2Score.setText(player2.getName() + ": " + player2.getScore());
		p2Score.setBackgroundColor(Color.DARK_GRAY);

		markSelectable(spointx, spointy);

		if (turn) {
			for (int i = 0; i < mapSize; i++) {
				for (int k = 0; k < mapSize; k++) {
					if (maxitmap[i][k].getState() == 1) {
						if (maxitmap[i][k].inbound(mX, mY)) {
							maxitmap[i][k].setState(2);
							if (Gdx.input.isTouched()) {
								undoSelectable(spointx, spointy);
								maxitmap[i][k].setState(3);
								maxitmap[i][k].button.playSound();
								spointx = i;
								spointy = k;
								player1.addScore(maxitmap[i][k].getNumber());
								turn = false;
							}
						} else {
							maxitmap[i][k].setState(1);
						}
					}
				}
			}
		} else {
			greedyAI();
		}
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);

		fps.render(Color.WHITE, batch, sr);

		p1Score.render(Color.WHITE, batch, sr);
		p2Score.render(Color.WHITE, batch, sr);

		sr.begin(ShapeType.Filled);
		sr.setColor(0.90f, 0.90f, 0.90f, 1f);
		sr.rect((1280 - (72 * mapSize + 24)) / 2, (720 - (72 * mapSize + 24)) / 2, 72 * mapSize + 24,
				72 * mapSize + 24);
		sr.end();

		for (int i = 0; i < mapSize; i++) {
			for (int k = 0; k < mapSize; k++) {
				maxitmap[i][k].draw(mX, mY, batch, sr);
			}
		}
		menu.render(mX, mY, batch, sr);
		reset.render(mX, mY, batch, sr);
	}

	public void markSelectable(int spointx, int spointy) {

		for (int i = 0; i < mapSize; i++) {
			maxitmap[i][spointy].setState(1);
		}

		for (int i = 0; i < mapSize; i++) {
			maxitmap[spointx][i].setState(1);
		}

		maxitmap[spointx][spointy].setState(3);
	}

	public void undoSelectable(int spointx, int spointy) {

		for (int i = 0; i < mapSize; i++) {
			for (int k = 0; k < mapSize; k++) {
				if (maxitmap[i][k].getState() == 1 || maxitmap[i][k].getState() == 2) {
					maxitmap[i][k].setState(0);
				}
			}
		}
	}

	public void greedyAI() {
		int maxvalue = -(mapSize * mapSize);
		int x = 0, y = 0;

		for (int i = spointx; i < mapSize; i++) {
			if (maxitmap[i][spointy].getNumber() > maxvalue && maxitmap[i][spointy].getState() != 3) {
				maxvalue = maxitmap[i][spointy].getNumber();

				x = i;
				y = spointy;
			}
		}

		for (int i = 0; i < spointx; i++) {
			if (maxitmap[i][spointy].getNumber() > maxvalue && maxitmap[i][spointy].getState() != 3) {
				maxvalue = maxitmap[i][spointy].getNumber();

				x = i;
				y = spointy;
			}
		}

		for (int k = spointy; k < mapSize; k++) {
			if (maxitmap[spointx][k].getNumber() > maxvalue && maxitmap[spointx][k].getState() != 3) {
				maxvalue = maxitmap[spointx][k].getNumber();

				x = spointx;
				y = k;
			}
		}

		for (int k = 0; k < spointy; k++) {
			if (maxitmap[spointx][k].getNumber() > maxvalue && maxitmap[spointx][k].getState() != 3) {
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
		turn = true;
	}

}
