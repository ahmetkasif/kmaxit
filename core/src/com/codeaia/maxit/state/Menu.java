package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;

public class Menu extends State {
	private Sprite bg;
	private Button singleplayer, options, credits, exit;
	
	public Menu(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();

		bg = new Sprite(new Texture(Gdx.files.internal("img/menu/menubg.png")));

		singleplayer = new Button("img/ui/720p/play", Game.width / 64,
				Game.height / 4);
		options = new Button("img/ui/720p/options", Game.width / 64,
				Game.height * 3 / 16);
		credits = new Button("img/ui/720p/credits", Game.width / 64,
				Game.height * 2 / 16);
		exit = new Button("img/ui/720p/exit", Game.width / 64,
				Game.height * 1 / 16);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if (singleplayer.isClicked(mX, mY)) {
			Game.state = 2;
			Game.singleplayer = new Singleplayer(2);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			singleplayer.playSound();
		}

		if (options.isClicked(mX, mY)) {
			Game.state = 3;
			Game.options = new Options(3);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			options.playSound();
		}

		if (credits.isClicked(mX, mY)) {
			Game.state = 4;
			Game.credits = new Options(4);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
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

		batch.begin();
		bg.draw(batch);
		batch.end();

		singleplayer.render(mX, mY);
		options.render(mX, mY);
		credits.render(mX, mY);
		exit.render(mX, mY);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
