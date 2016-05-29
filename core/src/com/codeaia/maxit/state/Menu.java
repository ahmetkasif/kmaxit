package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;
import com.codeaia.maxit.ui.Text;

public class Menu extends State {
	private Sprite bg;
	private Button play, help, options, credits, exit;
	private Text text;

	public Menu(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();

		bg = new Sprite(new Texture(Gdx.files.internal("img/menu/menubg.png")));
		text = new Text("Powered by LibGDX!", Gdx.graphics.getWidth() * 2 / 3, 100);

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
			Game.singleplayer = new Singleplayer(2);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.help != null) {
				Game.help.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			play.playSound();
		}

		if (help.isClicked(mX, mY)) {
			Game.state = 3;
			Game.help = new Help(3);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			help.playSound();
		}

		if (options.isClicked(mX, mY)) {
			Game.state = 4;
			Game.options = new Options(4);
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
			Game.state = 5;
			Game.credits = new Credits(5);
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

		fps.render(Color.WHITE, batch, sr);

		play.render(mX, mY, batch, sr);
		help.render(mX, mY, batch, sr);
		options.render(mX, mY, batch, sr);
		credits.render(mX, mY, batch, sr);
		exit.render(mX, mY, batch, sr);
		text.render(Color.WHITE, batch, sr);

	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
