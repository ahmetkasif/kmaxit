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

	private int[] duration = { 100, 100, 100, 100, 100};
	private int number;
	private int x, y;
	public int texturesize;
	private boolean selected, selectable, mouseOver;

	public Number(int x, int y) throws SlickException {

		base = new Image("/res/img/level1/base.png");
		selectablebase = new Image("/res/img/level1/animbase1.png");
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
		
		selected = false;
		selectable = false;

	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean inbound(float mouseX, float mouseY) {
		if (mouseX > x && mouseX < x + texturesize && mouseY > y && mouseY < y + texturesize) {
			return true;
		}
		return true;
	}

	public void draw(Graphics g) {

		// base condition
		if (!isSelectable() && !isSelected()) {
			g.drawImage(base, x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24,y + 24);
		}

		// If it is selectable,
		else if (isSelectable() && !isSelected() && !isMouseOver()) {
			g.drawImage(selectablebase, x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24,y + 24);
		}

		// If it is selectable and mouseOver,
		else if (isMouseOver()) {
			anime.draw(x, y);
			g.setColor(Color.black);
			g.drawString("" + number, x + 24, y + 24);
		}

		// if it is selected
		else if (isSelected()) {
			g.drawImage(selectedbase, x, y);
			g.setColor(Color.white);
			g.drawString("" + number, x + 24, y + 24);
		}

	}
}
