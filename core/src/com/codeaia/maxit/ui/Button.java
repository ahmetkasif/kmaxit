package com.codeaia.maxit.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.codeaia.maxit.controller.Constants;
import com.codeaia.maxit.controller.Game;

public class Button {
	public String text;
	private float x, y, width, height;
	private int offset = 11;
	private boolean checkbox, checked;
	private NinePatch np, np_hover, np_checked, np_checked_hover;
	private int key;
	private BitmapFont font;
	private GlyphLayout layout;

	public Button(String text, float x, float y, int key) {
		font = new BitmapFont();
		layout = new GlyphLayout();

		this.key = key;
		this.text = text;
		setX(x);
		setY(y);

		layout.setText(font, text);

		setWidth((2 * offset) + layout.width);
		setHeight(11 + (2 * offset));

		np = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np.png")), 16, 16, 16, 16);
		np_hover = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_hover.png")), 16, 16, 16, 16);
		np_checked = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_checked.png")), 16, 16, 16, 16);
		np_checked_hover = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_checked_hover.png")), 16, 16, 16,
				16);

		checkbox = false;
		checked = false;
	}

	public Button(String text, float x, float y) {
		font = new BitmapFont();
		layout = new GlyphLayout();

		this.text = text;
		setX(x);
		setY(y);
		key = -1;

		layout.setText(font, text);

		setWidth((2 * offset) + layout.width);
		setHeight(11 + (2 * offset));

		np = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np.png")), 16, 16, 16, 16);
		np_hover = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_hover.png")), 16, 16, 16, 16);
		np_checked = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_checked.png")), 16, 16, 16, 16);
		np_checked_hover = new NinePatch(new Texture(Gdx.files.internal("gfx/ui/np_checked_hover.png")), 16, 16, 16,
				16);

		checkbox = false;
		checked = false;
	}

	public Boolean isHovered(float mX, float mY) {
		if (mX > x * Constants.scale && mX < (x + width) * Constants.scale && mY > y * Constants.scale
				&& mY < (y + height) * Constants.scale) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isClicked(float mX, float mY) {
		if (isHovered(mX, mY) && Gdx.input.justTouched()) {
			Game.playClickSound();
			return true;
		}
		return false;
	}

	public void render(float mX, float mY, SpriteBatch batch, ShapeRenderer sr) {
		batch.begin();
		if (checkbox) {
			if (checked) {
				if (!isHovered(mX, mY)) {
					np_checked.draw(batch, x * Constants.scale, y * Constants.scale, width * Constants.scale,
							height * Constants.scale);
				} else {
					np_checked_hover.draw(batch, x * Constants.scale, y * Constants.scale, width,
							height * Constants.scale);
				}
			} else {
				if (!isHovered(mX, mY)) {
					np.draw(batch, x * Constants.scale, y * Constants.scale, width * Constants.scale,
							height * Constants.scale);
				} else {
					np_hover.draw(batch, x * Constants.scale, y * Constants.scale, width * Constants.scale,
							height * Constants.scale);
				}
			}
		} else {
			if (!isHovered(mX, mY)) {
				np.draw(batch, x * Constants.scale, y * Constants.scale, width * Constants.scale,
						height * Constants.scale);
			} else {
				np_hover.draw(batch, x * Constants.scale, y * Constants.scale, width * Constants.scale,
						height * Constants.scale);
			}
		}
		batch.end();

		if (key == -1) {
			batch.begin();
			font.draw(batch, text, (x + offset) * Constants.scale, (y + 2 * offset) * Constants.scale);
			batch.end();

		} else {
			batch.begin();
			font.setColor(Color.WHITE);
			font.draw(batch, text, (x + offset) * Constants.scale, (y + 2 * offset) * Constants.scale);
			font.setColor(Color.ORANGE);
			font.draw(batch, "" + text.charAt(0), (x + offset) * Constants.scale, (y + 2 * offset) * Constants.scale);
			batch.end();
		}

	}

	public GlyphLayout getLayout() {
		return this.layout;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getKey() {
		return key;
	}

}
