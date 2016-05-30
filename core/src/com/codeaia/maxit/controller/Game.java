package com.codeaia.maxit.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.codeaia.maxit.state.Credits;
import com.codeaia.maxit.state.Help;
import com.codeaia.maxit.state.Menu;
import com.codeaia.maxit.state.Options;
import com.codeaia.maxit.state.Singleplayer;
import com.codeaia.maxit.state.State;

public class Game extends ApplicationAdapter {
	public static int state;
	public static float mX, mY;
	private Music music;
	public Preferences preferences;
	public Cursor cursor;

	public static State menu, singleplayer, help, options, credits;

	@Override
	public void create() {
		cursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("gfx/cursors/normal.png")), 0, 0);
		preferences = getPrefs();
		if (!(preferences.getBoolean("valid"))) {
			Gdx.app.log("Constants Initialization", "Seems we got a new user!");
			preferences = Gdx.app.getPreferences("kmaxit_pref");

			preferences.putBoolean("valid", true);

			preferences.putInteger("width", 1280);
			preferences.putInteger("height", 720);
			preferences.putFloat("scale", 1);
			preferences.putBoolean("fullscreen", false);
			preferences.putBoolean("vsync", true);
			preferences.putBoolean("fpsEnable", false);

			preferences.putFloat("soundVolume", 0.65f);
			preferences.putFloat("musicVolume", 0.8f);

			preferences.putString("playerName", "Guest");

			preferences.flush();
			Constants.loadConstants();
		} else {
			Gdx.app.log("Constant Initialization", "Welcome back!");
			Constants.loadConstants();

			if (Gdx.graphics.supportsDisplayModeChange()) {
				if (Constants.fullscreen && !Gdx.graphics.isFullscreen()) {
					Gdx.app.log("DisplayModeChange", "Set to fullscreen");
					DisplayMode mode = Gdx.graphics.getDisplayMode();
					Gdx.graphics.setFullscreenMode(mode);
					Constants.width = mode.width;
					Constants.height = mode.height;
					Constants.scale = mode.width / 1280f;
				} else if (!Constants.fullscreen && Gdx.graphics.isFullscreen()) {
					Gdx.app.log("DisplayModeChange", "Set to windowed");
					Gdx.graphics.setWindowedMode(Constants.width, Constants.height);
					Constants.scale = Constants.width / 1280f;
				}
				Gdx.graphics.setVSync(Constants.vsync);
			}
		}

		mX = 0;
		mY = 0;

		music = Gdx.audio.newMusic(Gdx.files.internal("music/maintheme.ogg"));
		music.setVolume(Constants.musicVolume);
		music.play();
		music.setLooping(true);
		
		if (music.isPlaying()) {
			System.out.println("music playing!");
		}

		state = 1;
		menu = new Menu(1);
		singleplayer = new Singleplayer(2);
		help = new Help(3);
		options = new Options(4);
		credits = new Credits(5);
	}

	private void update(float delta) {
		mX = Gdx.input.getX();
		mY = Gdx.graphics.getHeight() - Gdx.input.getY();

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
		update(Gdx.graphics.getRawDeltaTime());
		
		Gdx.gl30.glClearColor(1, 1, 1, 1);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL30.GL_COVERAGE_BUFFER_BIT_NV : 0));
		
		Gdx.graphics.setCursor(cursor);
		
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
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public static Preferences getPrefs() {
		return Gdx.app.getPreferences("kmaxit_pref");
	}
}
