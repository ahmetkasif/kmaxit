package com.codeaia.maxit.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.codeaia.maxit.state.Menu;
import com.codeaia.maxit.state.State;

public class Game extends ApplicationAdapter {

	public static float width = 1280;
	public static float height = 720;
	public static float scale = 1;
	public static int state;
	public static float mX, mY, delta;
	private Music music;
	public Preferences preferences;

	public static State menu, singleplayer, help, options, credits;

	@Override
	public void create() {
		preferences = Gdx.app.getPreferences("kmaxit settings");
		
		mX = 0;
		mY = 0;
		delta = 0;

		music = Gdx.audio.newMusic(Gdx.files.internal("sound/maintheme.ogg"));
		music.setVolume(0.4f);
		music.play();

		state = 1;
		menu = new Menu(1);
	}

	private void update() {
		mX = Gdx.input.getX();
		mY = Gdx.graphics.getHeight() - Gdx.input.getY();
		delta = Gdx.graphics.getRawDeltaTime();

		if (state == 1) {
			if (!menu.equals(null)) {
				menu.update(mX, mY, delta);
			}
		} else if (state == 2) {
			if (!singleplayer.equals(null)) {
				singleplayer.update(mX, mY, delta);
			}
		} else if (state == 3) {
			if (!help.equals(null)) {
				help.update(mX, mY, delta);
			}
		} else if (state == 4) {
			if (!options.equals(null)) {
				options.update(mX, mY, delta);
			}
		} else if (state == 5) {
			if (!credits.equals(null)) {
				credits.update(mX, mY, delta);
			}
		}
	}

	@Override
	public void render() {
		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT
				| GL30.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL30.GL_COVERAGE_BUFFER_BIT_NV
						: 0));

		if (state == 1) {
			if (!menu.equals(null)) {
				menu.render(mX, mY);
			}
		} else if (state == 2) {
			if (!singleplayer.equals(null)) {
				singleplayer.render(mX, mY);
			}
		} else if (state == 3) {
			if (!help.equals(null)) {
				help.render(mX, mY);
			}
		} else if (state == 4) {
			if (!options.equals(null)) {
				options.render(mX, mY);
			}
		} else if (state == 5) {
			if (!credits.equals(null)) {
				credits.render(mX, mY);
			}
		}
	}
	
	@Override
	public void resize(int width, int height){
		Game.width = width;
		Game.height = height;
		if((1280 / width) / (720 / height) == 16 / 9){
			scale = width / 1280;
			System.out.println("yes!");
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
