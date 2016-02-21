package com.codeaia.maxit.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Checkbox {
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private Sound clickSound;
	private float x, y;
	
	public Checkbox(){
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		
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
			sr.setColor(Color.WHITE);
			sr.rect(x, y, 40, 40);
			sr.end();
			batch.end();
		} else{
			batch.begin();
			sr.begin(ShapeType.Line);
			sr.setColor(Color.CYAN);
			sr.rect(x, y, 40, 40);
			sr.end();
			batch.end();
		}
	}
	
	public Boolean isHovered(float mX, float mY){
		if(mX > x && mX < x + 40 && mY > y && mY < y + 40){
			return true;
		}else{
			return false;
		}
	}

}
