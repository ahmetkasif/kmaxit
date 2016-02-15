package com.codeaia.maxit.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.codeaia.maxit.controller.Game;
import com.codeaia.maxit.ui.Text;

public class Credits extends State {
	private Text brand, titleDev, titleGfx, titleAlg, dev1, dev2, dev3, dev4, dev5, gfx1, alg1, alg2, footBrand;
	private Sprite kitten;
	private float x;

	public Credits(int id) {
		super(id);
		create();
		
		x = 0;

		brand = new Text("Kitten Maxit: Plus", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x);
		
		titleDev = new Text("Developers:", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 40);
		dev1 = new Text("Abdullah Ögük", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 70);
		dev2 = new Text("Ahmet Kasif", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 90);
		dev3 = new Text("Hilmi Araz", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 110);
		dev4 = new Text("Mustafa Özdemir", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 130);
		dev5 = new Text("Ugur Kafali", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 150);
		
		titleGfx = new Text("Graphics and UI Design", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 190);
		gfx1 = new Text("Ahmet Kasif", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 220);
		
		titleAlg = new Text("AI", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 260);
		alg1 = new Text("Abdullah Ögük", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 290);
		alg2 = new Text("Ahmet Kasif", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 310);
		
		kitten = new Sprite(new Texture(Gdx.files.internal("img/kcode.png")));
		kitten.setPosition(Gdx.graphics.getWidth() / 2 - 40 - kitten.getWidth() / 2, Gdx.graphics.getHeight() / 2 - x - 330 - kitten.getHeight());
		
		footBrand = new Text("Kitten Maxit - Plus v1.1 / Feb 2016", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - x - 360 - kitten.getHeight());
		
	}

	@Override
	public void create() {
		super.create();
	}

	@Override
	public void update(float mX, float mY, float delta) {
		super.update(mX, mY, delta);
		
		x += delta * 120;
		
		if(x  > 900){
			x = - Gdx.graphics.getHeight() / 2;
		}
		
		brand.setY(Gdx.graphics.getHeight() / 2 + x);
		
		titleDev.setY(Gdx.graphics.getHeight() / 2 + x - 40);
		dev1.setY(Gdx.graphics.getHeight() / 2 + x - 70);
		dev2.setY(Gdx.graphics.getHeight() / 2 + x - 90);
		dev3.setY(Gdx.graphics.getHeight() / 2 + x - 110);
		dev4.setY(Gdx.graphics.getHeight() / 2 + x - 130);
		dev5.setY(Gdx.graphics.getHeight() / 2 + x - 150);
		
		titleGfx.setY(Gdx.graphics.getHeight() / 2 + x - 190);
		gfx1.setY(Gdx.graphics.getHeight() / 2 + x - 220);
		
		titleAlg.setY(Gdx.graphics.getHeight() / 2 + x - 260);
		alg1.setY(Gdx.graphics.getHeight() / 2 + x - 280);
		alg2.setY(Gdx.graphics.getHeight() / 2 + x - 300);
		
		kitten.setPosition(Gdx.graphics.getWidth() / 2 - 80 - kitten.getWidth() / 2, Gdx.graphics.getHeight() / 2 + x - 330 - kitten.getHeight());		
		footBrand.setY(Gdx.graphics.getHeight() / 2 + x - 360 - kitten.getHeight());
		
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
		brand.render(Color.RED);
		
		titleDev.render(Color.CYAN);
		dev1.render(Color.WHITE);
		dev2.render(Color.WHITE);
		dev3.render(Color.WHITE);
		dev4.render(Color.WHITE);
		dev5.render(Color.WHITE);
		
		titleGfx.render(Color.CYAN);
		gfx1.render(Color.WHITE);
		
		titleAlg.render(Color.CYAN);
		alg1.render(Color.WHITE);
		alg2.render(Color.WHITE);
		
		batch.begin();
		kitten.draw(batch);
		batch.end();
	
		footBrand.render(Color.LIGHT_GRAY);
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
