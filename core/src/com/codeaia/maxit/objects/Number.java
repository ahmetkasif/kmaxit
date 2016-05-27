package com.codeaia.maxit.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.codeaia.maxit.state.Singleplayer;
import com.codeaia.maxit.ui.Button;

public class Number {
	public Button button;
	private Random random;
	private int number;
	private int x, y;
	private int state;

	public Number(int x, int y) {
		random = new Random();
		number = random.nextInt() % (Singleplayer.mapSize * Singleplayer.mapSize);

		button = new Button("" + number, x, y, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		button.setWidth(64);
		button.setHeight(64);
		button.text.setY(button.text.getY() + 20);

		this.x = x;
		this.y = y;

		if (number < 0) {
			button.text.setX(button.text.getX() + 10);
		} else if (number >= 0 && number < 10) {
			button.text.setX(button.text.getX() + 16);
		} else if (number >= 10) {
			button.text.setX(button.text.getX() + 12);
		}
	}

	public void setY(int y) {
		this.y = y;

	}

	public void setX(int x) {
		this.x = x;
	}

	public int getNumber() {
		return number;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getState() {
		return state;
	}

	public void reset() {
		this.state = 0;
	}

	public void setState(int state) {
		if (this.state != 3) {
			this.state = state;
		}
	}

	public boolean inbound(float mouseX, float mouseY) {
		if (mouseX > x && mouseX < x + 64 && mouseY > y && mouseY < y + 64) {
			return true;
		}
		return false;
	}

	public void draw(float mX, float mY, SpriteBatch batch, ShapeRenderer sr) {
		button.render(mX, mY, batch, sr);

		switch (state) {
		case 0:
			button.renderCustom(mX, mY, batch, sr, Color.WHITE);
			break;
		case 1:
			button.renderCustom(mX, mY, batch, sr, Color.FOREST);
			break;
		case 2:
			button.renderCustom(mX, mY, batch, sr, Color.FIREBRICK);
			// TODO flashy animation
			break;
		case 3:
			button.renderCustom(mX, mY, batch, sr, Color.GRAY);
			break;
		}
	}
}
