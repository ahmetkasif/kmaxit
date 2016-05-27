package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;

public class Options extends State {
	private Sprite bg;
	private Button menu, graphics, audio; 
	private int layer;
	
	public Options(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		bg = new Sprite(new Texture(	Gdx.files.internal("img/optionsbg.png")));
		
		menu = new Button("Menu", 32, 36, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		
		graphics = new Button("Graphics", 32, 108, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		audio = new Button("Audio", 32, 144, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		
		layer = 0;
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);
		
		if(menu.isClicked(mX, mY)){
			Game.menu = new Menu(1);
			Game.state = 1;
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.help != null) {
				Game.help.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			menu.playSound();
		}

		if(layer == 0){
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				Game.menu = new Menu(1);
				Game.state = 1;
				if (Game.singleplayer != null) {
					Game.singleplayer.destroy();
				}
				if (Game.options != null) {
					Game.options.destroy();
				}
				if (Game.credits != null) {
					Game.credits.destroy();
				}
			}
			if(graphics.isClicked(mX, mY)){
				layer = 1;
			}

			if(audio.isClicked(mX, mY)){
				layer = 2;
			}
		} else if(layer == 1){
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				layer = 0;
			}
		} else if(layer == 2){
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				layer = 0;
			}
		}
		
		
		
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		batch.begin();
		bg.draw(batch);
		batch.end();
		
		fps.render(Color.WHITE, batch, sr);
		
		menu.render(mX, mY, batch, sr);
		
		if(layer == 0){
			graphics.render(mX, mY, batch, sr);
			audio.render(mX, mY, batch, sr);
		} else if(layer == 1){
			// TODO graphics preferences
		} else if(layer == 2){
			// TODO audio preferences
		}
		
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
