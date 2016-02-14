package com.codeaia.maxit.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codeaia.maxit.controller.Game;

public class Button {
	private SpriteBatch batch;
	private Sprite sprite, spriteHover;
	
	private Sound clickSound;
	
	private String title;
	private float x, y;
	private int width, height;
	
	public Button(String path, float x, float y){
		setTitle(path);
		setX(x);
		setY(y);
		
		batch = Game.batch;
		sprite = new Sprite(new Texture(path + ".png"));
		sprite.setPosition(x, y);
		spriteHover = new Sprite(new Texture(path + "H.png"));
		spriteHover.setPosition(x, y);
		setWidth((int) sprite.getWidth());
		setHeight((int) sprite.getHeight());
		
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
		if(isHovered(mX, mY)){
			batch.begin();
			spriteHover.draw(batch);
			batch.end();
		}else{
			batch.begin();
			sprite.draw(batch);
			batch.end();
		}
	}
	
	public Boolean isHovered(float mX, float mY){
		if(mX > x && mX < x + width && mY > y && mY < y + height){
			return true;
		}else{
			return false;
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
