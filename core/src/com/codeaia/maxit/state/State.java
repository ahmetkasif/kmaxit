package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.codeaia.maxit.controller.Constants;
import com.codeaia.maxit.ui.Button;

public class State {
	public int id;
	public SpriteBatch batch;
	public ShapeRenderer sr;
	public Button fps;
	public Sprite[] bg = new Sprite[2];

	public State(int id) {
		this.id = id;
	}

	public void create() {
		batch = new SpriteBatch();
		sr = new ShapeRenderer();

		bg[0] = new Sprite(new Texture(Gdx.files.internal("gfx/loadingscreens/layout.png")));
		bg[1] = new Sprite(new Texture(Gdx.files.internal("gfx/loadingscreens/layout-dif.png")));

		fps = new Button("Fps: " + 60, 32, 678);

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				fps.text = "Fps: " + (int) (1 / Gdx.graphics.getDeltaTime());
			}
		}, 1f, 1f);
	}

	public void update(float mX, float mY, float delta) {
	}

	public void render(float mX, float mY) {
		batch.begin();
		if (Constants.scale == 1) {
			bg[0].draw(batch);
		} else {
			bg[1].draw(batch);
		}
		batch.end();
		
		if (Constants.fpsEnable) {
			fps.render(mX, mY, batch, sr);
		}

	}

}
