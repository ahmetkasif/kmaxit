package com.codeaia.maxit.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Button {
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private Text text;
	private Sound clickSound;
	private Color bgColor, textColor, hoverColor, borderColor;
	private float x, y;
	
	public Button(String value, float x, float y, Color bgColor, Color textColor, Color hoverColor, Color borderColor){
		text = new Text(value, x, y);
		setX(x);
		setY(y);
		setBgColor(bgColor);
		setTextColor(textColor);
		setBorderColor(borderColor);
		setHoverColor(hoverColor);
		
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sound/clicksound.wav"));
	}
	
	public boolean isClicked(float mX, float mY){
		if(isHovered(mX, mY) && Gdx.input.justTouched()){
			return true;
		} return false;
	}
	
	public void playSound(){
		clickSound.play();
	}
	
	public void render(float mX, float mY){
		if(!isHovered(mX, mY)){
			batch.begin();
			sr.begin(ShapeType.Line);
			sr.setColor(borderColor);
			sr.rect(x, y, 100, text.getHeight());
			sr.end();
			batch.end();
			text.render(textColor);
		} else{
			batch.begin();
			sr.begin(ShapeType.Line);
			sr.setColor(hoverColor);
			sr.rect(x, y, 100, text.getHeight());
			sr.end();
			batch.end();
			text.render(hoverColor);
		}
	}
	
	public Boolean isHovered(float mX, float mY){
		if(mX > x && mX < x + 100 && mY > y && mY < y + text.getHeight()){
			return true;
		}else{
			return false;
		}
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

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getHoverColor() {
		return hoverColor;
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	public void destroy() {
		try {
			this.finalize();
		} catch (Throwable e) {
			Gdx.app.log("Button", "Button finalization error", e);
			e.printStackTrace();
		}
	}

}
