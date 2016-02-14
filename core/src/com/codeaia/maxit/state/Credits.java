package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.codeaia.maxit.controller.Game;

public class Credits extends State {

	public Credits(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
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

	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		batch.begin();

		batch.end();
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
