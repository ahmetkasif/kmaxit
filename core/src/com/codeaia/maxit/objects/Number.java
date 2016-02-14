package com.codeaia.maxit.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codeaia.maxit.ui.Text;

public class Number {
	private SpriteBatch batch;
	private Sprite base, selectablebase, animbase1, animbase2, animbase3,
			selectedbase;
	private Text text;
	
	private Random random;
	private int number;
	private int x, y;
	public int texturesize;
	private int state; // 0 for base, 1 for selectable, 2 for selectable and mouseover, 3 for selected.

	public Number(int x, int y) {
		batch = new SpriteBatch();
		base = new Sprite(new Texture(Gdx.files.internal("img/play/base.png")));
		base.setPosition(x, y);
		selectablebase = new Sprite(new Texture(Gdx.files.internal("img/play/selectablebase.png")));
		selectablebase.setPosition(x, y);
		animbase1 = new Sprite(new Texture(Gdx.files.internal("img/play/animbase1.png")));
		animbase1.setPosition(x, y);
		animbase2 = new Sprite(new Texture(Gdx.files.internal("img/play/animbase2.png")));
		animbase2.setPosition(x, y);
		animbase3 = new Sprite(new Texture(Gdx.files.internal("img/play/animbase3.png")));
		animbase3.setPosition(x, y);
		selectedbase = new Sprite(new Texture(Gdx.files.internal("img/play/selectedbase.png")));
		selectedbase.setPosition(x, y);

		random = new Random();
		number = random.nextInt() % 25;
		
		if(number < 0){
			text = new Text("" + number, x + 10, y + 20);
		}else if(number > 0 && number < 10){
			text = new Text("" + number, x + 14, y + 20);
		} else if(number >= 10){
			text = new Text("" + number, x + 12, y + 20);
		}
		
		
		this.x = x;
		this.y = y;

		texturesize = 64;

	}

	public int getNumber() {
		return number;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getState() {
		return state;
	}

	public void reset() {
		this.state = 0;
	}

	public void setState(int state) {
		if (this.state != 3) {
			this.state = state;
		}
	}

	public boolean inbound(float mouseX, float  mouseY) {
		if (mouseX > x && mouseX < x + texturesize && mouseY > y
				&& mouseY < y + texturesize) {
			return true;
		}
		return false;
	}

	public void draw() {
		switch (state) {
		case 0:
			batch.begin();
			base.draw(batch);
			batch.end();
			text.render(Color.BLACK);
			break;
		case 1:
			batch.begin();
			selectablebase.draw(batch);
			batch.end();
			text.render(Color.BLACK);
			break;
		case 2:
			batch.begin();
			animbase1.draw(batch);
			batch.end();
			text.render(Color.BLACK);
			// TODO make animation if possible out of animbase's
			break;
		case 3:
			batch.begin();
			selectedbase.draw(batch);
			batch.end();
			text.render(Color.BLACK);
			break;
		}
	}
}
