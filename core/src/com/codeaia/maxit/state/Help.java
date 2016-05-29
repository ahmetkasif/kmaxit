package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Button;
import com.codeaia.maxit.ui.Text;

public class Help extends State {
	Text hint1, hint2, hint3, hint4;
	Button menu;

	public Help(int id) {
		super(id);
		create();
	}

	@Override
	public void create() {
		super.create();

		menu = new Button("Menu", 32, 45);

		hint1 = new Text(
				"1) ESCAPE key makes you return to the previous screen, only if you are not on main menu. If this is the case, you exit the application!",
				64, 630);
		hint2 = new Text(
				"2) Returning menu and then coming back to play restarts the game, so you don't have to re-open the application for playing again!",
				64, 585);
		hint3 = new Text(
				"3) Kitten Maxit is a turn based point cathcing game, on your turn you select a point and it is added to your score, if you get higher score att the end, you beat your opponent :)",
				64, 540);
		hint4 = new Text(
				"4) Kitten Maxit developed at April 2015 using Slick2D, after Slick2D no longer gets updated, we remade it using LibGDX at Feb 2016 and renamed to Kitten Maxit+ !",
				64, 495);
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);

		if (menu.isClicked(mX, mY)) {
			Game.menu = new Menu(1);
			Game.state = 1;
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.help != null) {
				Game.help.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
			menu.playSound();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Game.menu = new Menu(1);
			Game.state = 1;
			if (Game.singleplayer != null) {
				Game.singleplayer.destroy();
			}
			if (Game.options != null) {
				Game.options.destroy();
			}
			if (Game.help != null) {
				Game.help.destroy();
			}
			if (Game.credits != null) {
				Game.credits.destroy();
			}
		}
	}

	@Override
	public void render(float mX, float mY) {
		super.render(mX, mY);
		fps.render(Color.WHITE, batch, sr);
		menu.render(mX, mY, batch, sr);
		hint1.render(Color.WHITE, batch, sr);
		hint2.render(Color.WHITE, batch, sr);
		hint3.render(Color.WHITE, batch, sr);
		hint4.render(Color.WHITE, batch, sr);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
