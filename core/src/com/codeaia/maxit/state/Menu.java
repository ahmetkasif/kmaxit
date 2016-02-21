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
	private Color color;
	private int count = 0;
	
	public Menu(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();
		
		color = new Color();

		bg = new Sprite(new Texture(Gdx.files.internal("img/menu/menubg.png")));
		text = new Text("Powered by LibGDX!", Gdx.graphics.getWidth() * 2 / 3, 100);
		
		play = new Button("Play", Game.width / 64,
				Game.height * 5 / 16, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		help = new Button("How To Play", Game.width / 64,
				Game.height / 4, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		options = new Button("Options", Game.width / 64,
				Game.height * 3 / 16, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		credits = new Button("Credits", Game.width / 64,
				Game.height * 2 / 16, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
		exit = new Button("Exit", Game.width / 64,
				Game.height * 1 / 16, Color.BLACK, Color.WHITE, Color.CYAN, Color.BLACK);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		switch(count % 8){
			case 0:
				color = new Color(200, 100, 50, 1); // red
				count++;
			case 1:
				color = new Color(200, 125, 50, 1); // orange to red
				count++;
			case 2:
				color = new Color(200, 150, 50, 1); // orange to yellow
				count++;
			case 3:
				color = new Color(200, 175, 50, 1); // yellow to orange
				count++;
			case 4:
				color = new Color(175, 175, 50, 1); // orange
				count++;
			case 5:
				color = new Color(200, 175, 50, 1); // yellow to orange
				count++;
			case 6:
				color = new Color(200, 150, 50, 1); // orange to yellow
				count++;
			case 7:
				color = new Color(200, 125, 50, 1); // orange to red
		}

		if (play.isClicked(mX, mY)) {
			Game.state = 2;
			Game.singleplayer = new Singleplayer(2);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if(Game.help != null){
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
		
		if(help.isClicked(mX, mY)){
			Game.state = 3;
			Game.help = new Help(3);
			if (Game.menu != null) {
				Game.menu.destroy();
			}
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if(Game.options != null){
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

		play.render(mX, mY);
		help.render(mX, mY);
		options.render(mX, mY);
		credits.render(mX, mY);
		exit.render(mX, mY);

		text.render(color);
		
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
