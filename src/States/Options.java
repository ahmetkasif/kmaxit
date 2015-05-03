package States;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Controller.Game;

public class Options extends BasicGameState {
	private String grtext, autext, backtext;;
	private Color grcolor, aucolor, backcolor;
	private int mouseX;
	private int mouseY;
	
	private Sound clickSound;

	public Options(int state) {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		grtext = "Graphics";
		autext = "Audio";
		backtext = "Back";

		grcolor = Color.white;
		aucolor = Color.white;
		backcolor = Color.white;
		
		clickSound = new Sound("/res/sound/clickSound.wav");

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.cyan);
		g.drawString("Kitten Maxit", (Game.width / 2) - 70, 50);
		
		g.drawString("" + mouseY, 1200, 30);

		g.setColor(grcolor);
		g.drawString(grtext, (Game.width / 2) - 50, (Game.height / 2) - 100);
		g.setColor(aucolor);
		g.drawString(autext, (Game.width / 2) - 50, (Game.height / 2) - 60);
		g.setColor(backcolor);
		g.drawString(backtext, (Game.width / 2) - 50, (Game.height / 2) - 20);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {

		mouseX = Mouse.getX();
		mouseY = Mouse.getY();

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}

		if ((mouseX > (Game.width / 2) - 50 && mouseX < (Game.width / 2) + 25)
				&& (mouseY < (Game.height / 2) + 100 && mouseY > (Game.height / 2) + 80)
				) {
			grcolor = Color.red;
			
			if(Mouse.isButtonDown(0)){
				clickSound.play();
				sbg.enterState(4);
			}
		} else {
			grcolor = Color.white;
		}
		
		if ((mouseX > (Game.width / 2) - 50 && mouseX < (Game.width / 2) - 3)
				&& (mouseY < (Game.height / 2) + 60 && mouseY > (Game.height / 2) + 40)
				) {
			aucolor = Color.red;
			if(Mouse.isButtonDown(0)){
				clickSound.play();
				sbg.enterState(5);
			}
		} else {
			aucolor = Color.white;
		}
		
		if ((mouseX > (Game.width / 2) - 50 && mouseX < (Game.width / 2) - 10)
				&& (mouseY < (Game.height / 2) + 20 && mouseY > (Game.height / 2))
				) {
			backcolor = Color.red;
			if(Mouse.isButtonDown(0)){
				clickSound.play();
				sbg.enterState(0);
			}
		} else {
			backcolor = Color.white;
		}

	}

	@Override
	public int getID() {
		return 2;
	}

}
