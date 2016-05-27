package com.codeaia.maxit.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Text {
	private BitmapFont font;
	private String text;
	private float x, y, width, height;
	private boolean bgColorEnabled;
	private Color backgroundColor;
	private GlyphLayout layout;
	
	public Text(String text, float x, float y) {
		font = new BitmapFont();
		layout = new GlyphLayout();
		this.text = text;
		layout.setText(font, text);
		
		setX(x);
		setY(y);
		setWidth(layout.width);
		setHeight(11);
		
		bgColorEnabled = false;
		this.y = y;
	}

	public void render(Color color, SpriteBatch batch, ShapeRenderer sr) {
		if (bgColorEnabled) {
			sr.begin(ShapeType.Filled);
			sr.setColor(backgroundColor);
			//sr.rect(x, y, width, height);
			sr.end();
		}
		batch.begin();
		font.setColor(color);
		font.draw(batch, text, x, y);
		batch.end();
	}

	public void setBackgroundColor(Color color) {
		bgColorEnabled = true;
		this.backgroundColor = color;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
