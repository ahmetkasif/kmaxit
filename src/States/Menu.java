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

public class Menu extends BasicGameState {
	Image button;

	public Menu(int state) {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		button = new Image("/res/spdynamic.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawRect(20, 20, 50, 26);
		g.drawString("Hello There", 500, 250);
		
		g.setColor(Color.cyan);
		g.fillRoundRect(650, 450, 60, 45, 13);
		
		g.drawImage(button, 100,200);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Display.destroy();
			System.exit(0);
		}

	}

	@Override
	public int getID() {
		return 0;
	}

}
