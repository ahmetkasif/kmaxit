package com.codeaia.maxit.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
	private SpriteBatch batch;
	private BitmapFont font;
	private String text;
	private float x, y;
	
	public Text(String text, float x, float y){
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.text = text;
		setX(x);
		setY(y);
	}
	
	public void render(Color color){
		batch.begin();
		font.setColor(color);
		font.draw(batch, text, x + 15, y + font.getLineHeight());
		batch.end();
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
		return font.getLineHeight() + font.getXHeight();
	}
	
}
