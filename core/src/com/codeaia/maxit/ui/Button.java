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
	private float x, y;
	
	public Button(String value, float x, float y){
		text = new Text(value, x, y);
		setX(x);
		setY(y);
		
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
			sr.setColor(Color.BLACK);
			sr.rect(x, y, 100, text.getHeight());
			sr.end();
			batch.end();
			text.render(Color.WHITE);
		} else{
			batch.begin();
			sr.begin(ShapeType.Line);
			sr.setColor(Color.BLACK);
			sr.rect(x, y, 100, text.getHeight());
			sr.end();
			batch.end();
			text.render(Color.CYAN);
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

	public void destroy() {
		try {
			this.finalize();
		} catch (Throwable e) {
			System.out.println("Button object couldn't be finalized");
			e.printStackTrace();
		}
	}

}
