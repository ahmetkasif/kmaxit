package States;

import Controller.Game;
import Objects.Number;

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

public class Multiplayer extends BasicGameState {
	private String backtext, exittext;
	private Color backcolor, exitcolor;
	private Sound clickSound;
	private float mouseX, mouseY;
	private Number[][] maxitmap = new Number[5][5];

	public Multiplayer(int state) {
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {

		backtext = "Menu";
		exittext = "Exit";

		backcolor = Color.white;
		exitcolor = Color.white;

		clickSound = new Sound("/res/sound/clickSound.wav");

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k] = new Number(i * 74, k * 74 + 80);
			}
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k].draw(g);
			}
		}

		g.setColor(Color.cyan);
		g.drawString("Kitten Maxit", (Game.width / 2) - 70, 20);

		g.setColor(backcolor);
		g.drawString(backtext, 40, Game.height - 75);
		
		g.setColor(exitcolor);
		g.drawString(exittext, Game.width - 100, 30);
		
		g.drawString("" + mouseY, Game.width - 100, 80);
		
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
		
		if( mouseX > Game.width - 100 && mouseX < Game.width - 60 && mouseY < Game.height - 30 && mouseY > Game.height - 45){
			exitcolor = Color.red;
			if(Mouse.isButtonDown(0)){
				clickSound.play();
				Display.destroy();
				System.exit(0);
			}
		}else{
			exitcolor = Color.white;
		}
		
		if( mouseX > 40 && mouseX < 80 && mouseY < 70 && mouseY > 58){
			backcolor = Color.red;
			if(Mouse.isButtonDown(0)){
				clickSound.play();
				sbg.enterState(0);
			}
		}else{
			backcolor = Color.white;
		}
		
	}

	@Override
	public int getID() {
		return 6;
	}

}
