package com.codeaia.maxit.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.codeaia.maxit.controller.Constants;
import com.codeaia.maxit.state.Singleplayer;
import com.codeaia.maxit.ui.Text;

public class Number {
	private Text text;
	private Random random;
	private int number;
	private int x, y;
	private int state;

	private static int offset = 32;
	private Sprite sprite, sprite_choose, sprite_choose_hover, sprite_chosen;

	public Number(int x, int y) {
		random = new Random();
		number = random.nextInt() % (Singleplayer.mapSize * Singleplayer.mapSize);
		text = new Text("" + number, x + offset, y + offset);

		sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ui/number.png")));
		sprite.setPosition(x * Constants.scale, y * Constants.scale);
		sprite_choose = new Sprite(new Texture(Gdx.files.internal("gfx/ui/number_choose.png")));
		sprite_choose.setPosition(x * Constants.scale, y * Constants.scale);
		sprite_choose_hover = new Sprite(new Texture(Gdx.files.internal("gfx/ui/number_choose_hover.png")));
		sprite_choose_hover.setPosition(x * Constants.scale, y * Constants.scale);
		sprite_chosen = new Sprite(new Texture(Gdx.files.internal("gfx/ui/number_chosen.png")));
		sprite_chosen.setPosition(x * Constants.scale, y * Constants.scale);

		this.x = x;
		this.y = y;

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
		if (mouseX > x * Constants.scale && mouseX < (x + 64) * Constants.scale && mouseY > y * Constants.scale
				&& mouseY < (y + 64) * Constants.scale) {
			return true;
		}
		return false;
	}

	public void draw(float mX, float mY, SpriteBatch batch, ShapeRenderer sr) {
		batch.begin();
		switch (state) {
		case 0:
			sprite.draw(batch);
			break;
		case 1:
			sprite_choose.draw(batch);
			break;
		case 2:
			sprite_choose_hover.draw(batch);
			break;
		case 3:
			sprite_chosen.draw(batch);
			break;
		}
		batch.end();
		text.render(Color.WHITE, batch, sr);
	}
}
