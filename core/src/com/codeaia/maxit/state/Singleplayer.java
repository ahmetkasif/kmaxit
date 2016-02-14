package com.codeaia.maxit.state;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.objects.Number;
import com.codeaia.maxit.objects.Player;

;

public class Singleplayer extends State {
	private Sprite bg;
	private Sprite table;
	private Player player1, computer;
	private Number[][] maxitmap = new Number[5][5];
	private int spointx, spointy;

	public Singleplayer(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		bg = new Sprite(new Texture(Gdx.files.internal("img/play/playbg.png")));
		table = new Sprite(
				new Texture(Gdx.files.internal("img/play/frame.png")));
		table.setPosition(Gdx.graphics.getWidth() / 2 - table.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - table.getHeight() / 2);

		player1 = new Player("You", true);
		computer = new Player("Computer (Easy)", false);

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k] = new Number(((int) Game.width / 2) - (64 * 2)
						+ i * 74 - 10, k * 74 + 200);
			}
		}

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

		markSelectable(spointx, spointy);

		if (player1.getTurn() && !computer.getTurn()) {
			for (int i = 0; i < 5; i++) {
				for (int k = 0; k < 5; k++) {
					if (maxitmap[i][k].getState() == 1) {
						if (maxitmap[i][k]
								.inbound(mX, Game.height - mY)) {
							maxitmap[i][k].setState(2);
							if (Gdx.input.isTouched()) {
								undoSelectable(spointx, spointy);
								maxitmap[i][k].setState(3);

								spointx = i;
								spointy = k;

								player1.addScore(maxitmap[i][k].getNumber());
								player1.setTurn(false);
								computer.setTurn(true);
							}
						} else {
							maxitmap[i][k].setState(1);
						}
					}
				}
			}
		}

		if (!player1.getTurn() && computer.getTurn()) {
			greedyAI();
		}
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		batch.begin();
		bg.draw(batch);
		table.draw(batch);
		batch.end();

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k].draw();
			}
		}
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

	public void restart() {
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k].reset();
			}
		}

		player1.resetScore();
		computer.resetScore();
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

		computer.addScore(maxitmap[x][y].getNumber());
		computer.setTurn(false);
		player1.setTurn(true);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
