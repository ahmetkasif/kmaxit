package States;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Controller.Game;

public class Credits extends BasicGameState {
	private String[] developers = { "Ahmet KASIF", "Abdullah OGUK",
			"Hilmi ARAZ", "Ugur KAFALI", "Mustafa OZDEMIR" };
	private int x, y;
	private int vertAlign;
	private Image kcode;
	
	private Color head, content;

	public Credits(int state) {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		x = (Game.width / 2) - 200;
		y = 500;
		vertAlign = 30;
		
		kcode = new Image("res/img/credits/kcode.png");
		
		head = new Color(Color.red);
		content = new Color(Color.white);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		g.setColor(head);
		g.drawString("PROGRAMMING", x + 150, y);
		g.setColor(content);
		g.drawString(developers[0], x + 150, y + vertAlign);
		g.drawString(developers[1], x + 150, y + (vertAlign * 2));
		g.drawString(developers[2], x + 150, y + (vertAlign * 3));
		g.drawString(developers[3], x + 150, y + (vertAlign * 4));
		g.drawString(developers[4], x + 150, y + (vertAlign * 5));

		g.setColor(head);
		g.drawString("GRAPHICS", x + 150, y + (vertAlign * 7));
		g.setColor(content);
		g.drawString(developers[0], x + 150, y + (vertAlign * 8));

		g.setColor(head);
		g.drawString("TESTING", x + 150, y + (vertAlign * 10));
		g.setColor(content);
		g.drawString(developers[1], x + 150, y + (vertAlign * 11));

		g.setColor(head);
		g.drawString("MUSICS", x + 150, y + (vertAlign * 13));
		g.setColor(content);
		g.drawString(developers[0], x + 150, y + (vertAlign * 14));

		g.setColor(head);
		g.drawString("Special thanks to;", x + 150, y + (vertAlign * 16));
		g.setColor(content);
		g.drawString("Uncopyrighted Musics", x + 150, y + (vertAlign * 17));
		g.drawString("KenneyDonation", x + 150, y + (vertAlign * 18));
		g.drawString("Slick2D Library Developers", x + 150, y + (vertAlign * 19));
		g.drawString("GitHub", x + 150, y + (vertAlign * 20));
		g.drawString("Celal Bayar University\n Computer Engineering Departmant Academicians", x + 150, y + (vertAlign * 21));
		
		
		g.drawImage(kcode, x + 150, y + (vertAlign * 25));
		
		g.setColor(content);
		g.drawString("KITTENCODE 2015", x + 150, y + (vertAlign * 28));

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		y--; 
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			sbg.enterState(0);
		}
		
		
		if(y + (vertAlign * 28) < - 50){
			y = 768;
		}
	}

	@Override
	public int getID() {
		return 3;
	}

}
