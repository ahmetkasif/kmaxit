package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
		play = new Button("Play", 32, 225);
		help = new Button("How To Play", 32, 180);
		options = new Button("Options", 32, 135);
		credits = new Button("Credits", 32, 90);
		exit = new Button("Exit", 32, 45);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if (play.isClicked(mX, mY)) {
			Game.state = 2;
			Game.singleplayer.create();
			play.playSound();
		}

		if (help.isClicked(mX, mY)) {
			Game.state = 3;
			Game.help.create();
			help.playSound();
		}

		if (options.isClicked(mX, mY)) {
			Game.state = 4;
			Game.options.create();
			options.playSound();
		}

		if (credits.isClicked(mX, mY)) {
			Game.state = 5;
			Game.credits.create();
			credits.playSound();
		}

		if (exit.isClicked(mX, mY)) {
			exit.playSound();
			Gdx.app.exit();
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
