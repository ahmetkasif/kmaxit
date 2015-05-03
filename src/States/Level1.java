package States;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level1 extends BasicGameState {

	public Level1(int state) {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			Menu.menuMusic.stop();
		}
	}

	@Override
	public int getID() {
		return 1;
	}

}
