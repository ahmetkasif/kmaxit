package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.codeaia.maxit.ui.Text;

public class State {
	public int id;
	public SpriteBatch batch;
	public ShapeRenderer sr;
	public Text fps;

	public State(int id) {
		this.id = id;
		batch = new SpriteBatch();
		this.sr = new ShapeRenderer();
	}

	public void create() {
		batch = new SpriteBatch();
		fps = new Text("Fps : " + 60, 32, 658);
		
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				fps.setText("Fps: " + (int) (1 / Gdx.graphics.getDeltaTime()));
			}
		}, 1f, 1f);
	}

	public void update(float mX, float mY, float delta) {
	}

	public void render(float mX, float mY) {
	}

	public void destroy() {
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
