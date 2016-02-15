package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;

public class Options extends State {
	private Sprite bg;
	private Button graphics, audio; 
	private int layer, state;
	
	public Options(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		bg = new Sprite(new Texture(	Gdx.files.internal("img/optionsbg.png")));
		
		graphics = new Button("Graphics", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 64);
		audio = new Button("Audio", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 64);
		
		
		
		layer = 0;
		state = 0;
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

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
		
		
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		batch.begin();
		bg.draw(batch);
		batch.end();
		
		if(layer == 0){
			graphics.render(mX, mY);
			audio.render(mX, mY);
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
