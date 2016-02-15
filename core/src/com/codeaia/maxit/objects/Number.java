package com.codeaia.maxit.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.codeaia.maxit.ui.Text;

public class Number {
	private ShapeRenderer sr;
	private Text text;
	
	private Random random;
	private int number;
	private int x, y;
	private int state;

	public Number(int x, int y) {
		sr = new ShapeRenderer();

		random = new Random();
		number = random.nextInt() % 25;
		
		if(number < 0){
			text = new Text("" + number, x + 10, y + 20);
		}else if(number >= 0 && number < 10){
			text = new Text("" + number, x + 14, y + 20);
		} else if(number >= 10){
			text = new Text("" + number, x + 12, y + 20);
		}
		
		this.x = x;
		this.y = y;
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
		if (mouseX > x && mouseX < x + 64 && mouseY > y
				&& mouseY < y + 64) {
			return true;
		}
		return false;
	}

	public void draw() {
		switch (state) {
		case 0:
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.WHITE);
			sr.rect(x, y, 64, 64);
 			sr.end();
			break;
		case 1:
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.FOREST);
			sr.rect(x, y, 64, 64);
 			sr.end();
			break;
		case 2:
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.FIREBRICK);
			sr.rect(x, y, 64, 64);
 			sr.end();
 			// TODO flashy animation
			break;
		case 3:
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.GRAY);
			sr.rect(x, y, 64, 64);
 			sr.end();
			break;
		}
		text.render(Color.BLACK);
	}
}
