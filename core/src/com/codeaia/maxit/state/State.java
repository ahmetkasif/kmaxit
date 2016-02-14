package com.codeaia.maxit.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class State {
	public int id;
	public SpriteBatch batch;

	public State(int id) {
		this.id = id;
	}

	public void create() {
		batch = new SpriteBatch();
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