package com.codeaia.maxit.state;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.codeaia.maxit.controller.Constants;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.objects.Number;
import com.codeaia.maxit.objects.Player;
import com.codeaia.maxit.ui.Button;

public class Singleplayer extends State {
	public static int mapSize;
	private Player player1, player2;
	private Number[][] maxitmap = new Number[7][7];
	private int spointx, spointy;
	private Button p1Score, p2Score;
	private Button menu, reset;
	private boolean turn;
	private NinePatch table;

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

		p1Score = new Button(player1.getName() + ": " + 0, 32, 540);
		p1Score.setWidth((2 * p1Score.getOffset()) + p1Score.getLayout().width + 16);
		p2Score = new Button(player2.getName() + ": " + 0, 1088, 540);
		p2Score.setWidth((2 * p2Score.getOffset()) + p2Score.getLayout().width + 16);

		table = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np.png")), 16, 16, 16, 16);

		for (int i = 0; i < mapSize; i++) {
			for (int k = 0; k < mapSize; k++) {
				maxitmap[i][k] = new Number((1280 - (72 * mapSize + 24)) / 2 + i * 72 + 16,
						(720 - (72 * mapSize + 24)) / 2 + k * 72 + 16);
			}
		}

		menu = new Button("Menu", 32, 45, Keys.M);
		reset = new Button("Reset", 32, 90, Keys.R);

		spointx = (new Random()).nextInt(mapSize);
		spointy = (new Random()).nextInt(mapSize);

		maxitmap[spointx][spointy].setState(3);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Game.menu.create();
			Game.state = 1;
		}

		if (menu.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(menu.getKey())) {
			Game.menu.create();
			Game.state = 1;
		}

		if (reset.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(reset.getKey())) {
			Game.singleplayer.create();
			Game.state = 2;
		}

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
								Game.playClickSound();
								spointx = i;
								spointy = k;

								player1.addScore(maxitmap[i][k].getNumber());
								p1Score.text = player1.getName() + ": " + player1.getScore();

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

		p1Score.render(mX, mY, batch, sr);
		p2Score.render(mX, mY, batch, sr);

		batch.begin();
		table.draw(batch, (640 - 12 * ((mapSize * 3) + 1)) * Constants.scale,
				12 * (29 - (mapSize * 3)) * Constants.scale, (24 * ((mapSize * 3) + 1)) * Constants.scale,
				(24 * ((mapSize * 3) + 1)) * Constants.scale);
		batch.end();

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
		p2Score.text = player2.getName() + ": " + player2.getScore();
		turn = true;
	}

}
