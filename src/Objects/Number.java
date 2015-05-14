package Objects;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Number {
	private Image base, selectablebase, animbase1, animbase2, animbase3,
			selectedbase;
	private Random random;
	private Animation anime;
	private Image[] animeimg = new Image[5];

	private int[] duration = { 100, 100, 100, 100, 100 };
	private int number;
	private int x, y;
	public int texturesize;
	private int state; // 0 for base, 1 for selectable, 2 for selectable and
						// mouseover, 3 for selected.

	public Number(int x, int y) throws SlickException {

		base = new Image("/res/img/level1/base.png");
		selectablebase = new Image("/res/img/level1/selectablebase.png");
		animbase1 = new Image("/res/img/level1/animbase1.png");
		animbase2 = new Image("/res/img/level1/animbase2.png");
		animbase3 = new Image("/res/img/level1/animbase3.png");
		selectedbase = new Image("/res/img/level1/selectedbase.png");

		animeimg[0] = animbase1;
		animeimg[1] = animbase2;
		animeimg[2] = animbase3;
		animeimg[3] = animbase2;
		animeimg[4] = animbase1;

		anime = new Animation(animeimg, duration);

		random = new Random();
		number = random.nextInt() % 25;

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

	public boolean inbound(float mouseX, float mouseY) {
		if (mouseX > x && mouseX < x + texturesize && mouseY > y
				&& mouseY < y + texturesize) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {

		switch (state) {
		case 0:
			g.drawImage(base, x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24, y + 24);
			break;
		case 1:
			g.drawImage(selectablebase, x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24, y + 24);
			break;
		case 2:
			anime.draw(x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24, y + 24);
			break;
		case 3:
			g.drawImage(selectedbase, x, y);
			g.setColor(Color.white);
			g.drawString("" + number, x + 24, y + 24);
			break;
		}
	}
}
