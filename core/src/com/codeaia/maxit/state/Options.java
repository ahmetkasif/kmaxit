package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.codeaia.maxit.controller.Constants;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;
import com.codeaia.maxit.ui.Text;

public class Options extends State {
	private int layer;
	private Button menu, graphics, audio, fullscreenOn, fullscreenOff, vSyncOn, vSyncOff, fpsOn, fpsOff;
	private Text fullscreen, vSync, fpsEnable;

	public Options(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		layer = 0;

		graphics = new Button("Graphics", 32, 108);
		audio = new Button("Audio", 32, 144);

		initOptionStage();
		initGraphicStage();
		initAudioStage();
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (menu.isClicked(mX, mY)) {
			Game.menu.create();
			Game.state = 1;
			menu.playSound();
		}

		if (graphics.isClicked(mX, mY)) {
			layer = 1;
		}

		if (audio.isClicked(mX, mY)) {
			layer = 2;
		}

		if (layer == 0) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				Game.menu.create();
				Game.state = 1;
			}
			if (graphics.isClicked(mX, mY)) {
				layer = 1;
			}

			if (audio.isClicked(mX, mY)) {
				layer = 2;
			}
		} else if (layer == 1) {

			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				layer = 0;
			}

			if (fullscreenOn.isClicked(mX, mY)) {
				if (!Game.getPrefs().getBoolean("fullscreen")) {
					if (Gdx.graphics.supportsDisplayModeChange()) {
						Gdx.app.log("DisplayModeChange", "Set to fullscreen");
						DisplayMode mode = Gdx.graphics.getDisplayMode();
						Gdx.graphics.setFullscreenMode(mode);
						Game.getPrefs().putBoolean("fullscreen", true);
						Game.getPrefs().flush();
						
						fullscreenOn.setChecked(true);
						fullscreenOff.setChecked(false);
						
						Constants.scale = mode.width / 1280f;
						
						Game.state = 1;
						Game.menu.create();
						Game.state = 4;
						Game.options.create();
						
						layer = 1;
					}
				}
			}

			if (fullscreenOff.isClicked(mX, mY)) {
				if (Game.getPrefs().getBoolean("fullscreen")) {
					if (Gdx.graphics.supportsDisplayModeChange()) {
						Gdx.app.log("DisplayModeChange", "Set to windowed");
						Gdx.graphics.setWindowedMode(Constants.width, Constants.height);
						Game.getPrefs().putBoolean("fullscreen", false);
						Game.getPrefs().flush();
						
						fullscreenOn.setChecked(false);
						fullscreenOff.setChecked(true);
						
						Constants.scale = Constants.width / 1280f;
						
						Game.state = 1;
						Game.menu.create();

						Game.state = 4;
						Game.options.create();
						
						layer = 1;
					}
				}
			}

			if (vSyncOn.isClicked(mX, mY)) {
				if (Gdx.graphics.supportsDisplayModeChange()) {
					Gdx.app.log("DisplayModeChange", "V-Sync On");
					Gdx.graphics.setVSync(true);
					Game.getPrefs().putBoolean("vsync", true);
					Game.getPrefs().flush();
					
					vSyncOn.setChecked(true);
					vSyncOff.setChecked(false);
				}
			}

			if (vSyncOff.isClicked(mX, mY)) {
				if (Gdx.graphics.supportsDisplayModeChange()) {
					Gdx.app.log("DisplayModeChange", "V-Sync Off");
					Gdx.graphics.setVSync(false);
					Game.getPrefs().putBoolean("vsync", false);
					Game.getPrefs().flush();
					
					vSyncOn.setChecked(false);
					vSyncOff.setChecked(true);
				}
			}
			
			if (fpsOn.isClicked(mX, mY)) {
					Constants.fpsEnable = true;
					Game.getPrefs().putBoolean("fpsEnable", true);
					Game.getPrefs().flush();
					
					fpsOn.setChecked(true);
					fpsOff.setChecked(false);
			}

			if (fpsOff.isClicked(mX, mY)) {
				Constants.fpsEnable = false;
				Game.getPrefs().putBoolean("fpsEnable", false);
				Game.getPrefs().flush();
				
				fpsOn.setChecked(false);
				fpsOff.setChecked(true);
			}

		} else if (layer == 2) {

			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				layer = 0;
			}
		}

	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		menu.render(mX, mY, batch, sr);
		graphics.render(mX, mY, batch, sr);
		audio.render(mX, mY, batch, sr);

		if (layer == 1) {
			fullscreen.render(Color.WHITE, batch, sr);
			fullscreenOn.render(mX, mY, batch, sr);
			fullscreenOff.render(mX, mY, batch, sr);

			vSync.render(Color.WHITE, batch, sr);
			vSyncOn.render(mX, mY, batch, sr);
			vSyncOff.render(mX, mY, batch, sr);

			fpsEnable.render(Color.WHITE, batch, sr);
			fpsOn.render(mX, mY, batch, sr);
			fpsOff.render(mX, mY, batch, sr);
		} else if (layer == 2) {
			// TODO audio preferences
		}

	}

	private void initOptionStage() {
		menu = new Button("Menu", 32, 45);
		graphics = new Button("Graphics", 32, 135);
		audio = new Button("Audio", 32, 180);
	}

	private void initGraphicStage() {
		fullscreen = new Text("Fullscreen :", 448, 445);
		fullscreenOn = new Button("Fullscreen", 548, 428);
		fullscreenOn.setWidth(fullscreenOn.getWidth() + 16);
		fullscreenOn.text.setX(fullscreenOn.text.getX() + 8);
		fullscreenOn.setCheckbox(true);
		fullscreenOff = new Button("Windowed", 688, 428);
		fullscreenOff.setWidth(fullscreenOff.getWidth() + 16);
		fullscreenOff.text.setX(fullscreenOff.text.getX() + 8);
		fullscreenOff.setCheckbox(true);

		if (Game.getPrefs().getBoolean("fullscreen")) {
			fullscreenOn.setChecked(true);
			fullscreenOff.setChecked(false);
		} else {
			fullscreenOn.setChecked(false);
			fullscreenOff.setChecked(true);
		}

		vSync = new Text("V-Sync :", 448, 490);
		vSyncOn = new Button("On", 568, 477);
		vSyncOn.setWidth(vSyncOn.getWidth() + 16);
		vSyncOn.text.setX(vSyncOn.text.getX() + 8);
		vSyncOn.setCheckbox(true);
		vSyncOff = new Button("Off", 668, 477);
		vSyncOff.setWidth(vSyncOff.getWidth() + 16);
		vSyncOff.text.setX(vSyncOff.text.getX() + 8);
		vSyncOff.setCheckbox(true);

		if (Game.getPrefs().getBoolean("vsync")) {
			vSyncOn.setChecked(true);
			vSyncOff.setChecked(false);
		} else {
			vSyncOn.setChecked(false);
			vSyncOff.setChecked(true);
		}

		fpsEnable = new Text("Fps :", 448, 535);
		fpsOn = new Button("On", 568, 518);
		fpsOn.setWidth(fpsOn.getWidth() + 16);
		fpsOn.text.setX(fpsOn.text.getX() + 8);
		fpsOn.setCheckbox(true);
		fpsOff = new Button("Off", 668, 518);
		fpsOff.setWidth(fpsOff.getWidth() + 16);
		fpsOff.text.setX(fpsOff.text.getX() + 8);
		fpsOff.setCheckbox(true);

		if (Game.getPrefs().getBoolean("fpsEnable")) {
			fpsOn.setChecked(true);
			fpsOff.setChecked(false);
		} else {
			fpsOn.setChecked(false);
			fpsOff.setChecked(true);
		}
	}

	private void initAudioStage() {
		
	}

}
