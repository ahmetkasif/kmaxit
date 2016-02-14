package com.codeaia.maxit.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codeaia.maxit.controller.Game;

public class Text {
	private SpriteBatch batch;
	private BitmapFont font;
	private String text;
	private float x, y;
	private Color color;
	
	public Text(String path, float x, float y){
		batch = Game.batch;
		font = new BitmapFont();
		setX(x);
		setY(y);
	}
	
	public void render(){
		batch.begin();
		font.draw(batch, text, x, y);
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
