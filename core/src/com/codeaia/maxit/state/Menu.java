package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;

public class Menu extends State {
	private Button play, help, options, credits, exit;

	public Menu(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		play = new Button("Play", 32, 225, Keys.P);
		help = new Button("How To Play", 32, 180, Keys.H);
		options = new Button("Options", 32, 135, Keys.O);
		credits = new Button("Credits", 32, 90, Keys.C);
		exit = new Button("Exit", 32, 45, Keys.E);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (exit.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
				|| Gdx.input.isKeyJustPressed(exit.getKey())) {
			Gdx.app.exit();
		}

		if (play.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(play.getKey())) {
			Game.state = 2;
			Game.singleplayer.create();
		}

		if (help.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(help.getKey())) {
			Game.state = 3;
			Game.help.create();
		}

		if (options.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(options.getKey())) {
			Game.state = 4;
			Game.options.create();
		}

		if (credits.isClicked(mX, mY) || Gdx.input.isKeyJustPressed(credits.getKey())) {
			Game.state = 5;
			Game.credits.create();
		}

	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		play.render(mX, mY, batch, sr);
		help.render(mX, mY, batch, sr);
		options.render(mX, mY, batch, sr);
		credits.render(mX, mY, batch, sr);
		exit.render(mX, mY, batch, sr);
	}

}
