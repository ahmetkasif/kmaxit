package States;

import java.util.Random;

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
import Objects.Number;
import Objects.Player;

public class Level1 extends BasicGameState {
	private String backtext, exittext;
	private Color backcolor, exitcolor;
	private Sound clickSound;
	private float mouseX, mouseY;
	private int texturesize;
	private Number[][] maxitmap = new Number[5][5];
	private Player player1, player2;
	@SuppressWarnings("unused")
	// but we used it :(
	private Random randomx, randomy; // starting point randomization
	private int spointx, spointy;
	private int pTurn; // if 1, player 1 plays; if 2, player 2.

	public Level1(int state) {
	}

	public void markSelectable(int spointx, int spointy) {

		for (int i = 0; i < 5; i++) {
			maxitmap[i][spointy].setState(1);
		}

		for (int i = 0; i < 5; i++) {
			maxitmap[spointx][i].setState(1);
		}

		maxitmap[spointx][spointy].setState(3);
	}

	public void undoSelectable(int spointx, int spointy) {

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (maxitmap[i][k].getState() == 1
						|| maxitmap[i][k].getState() == 2) {
					maxitmap[i][k].setState(0);
				}
			}
		}
	}

	public void restart() {
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k].reset();
			}
		}

		player1.resetScore();
		player2.resetScore();
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {

		player1 = new Player("player1", true);
		player2 = new Player("player2", false);

		texturesize = 64;

		backtext = "Menu";
		exittext = "Exit";

		backcolor = Color.white;
		exitcolor = Color.white;

		clickSound = new Sound("/res/sound/clickSound.wav");

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				maxitmap[i][k] = new Number((Game.width / 2)
						- (texturesize * 3) + i * 74, k * 74 + 200);
			}
		}

		spointx = (randomx = new Random()).nextInt(5);
		spointy = (randomy = new Random()).nextInt(5);

		maxitmap[spointx][spointy].setState(3);

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(Color.darkGray);

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

		g.setColor(Color.red);
		g.drawString(player1.getName() + " : " + player1.getScore(), 100, 200);

		g.setColor(Color.red);
		g.drawString(player2.getName() + " : " + player2.getScore(), 1100, 200);

		pTurn = 1;
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {

		mouseX = Mouse.getX();
		mouseY = Mouse.getY();

		markSelectable(spointx, spointy);

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (maxitmap[i][k].getState() == 1) {
					if (maxitmap[i][k].inbound(mouseX, Game.height - mouseY)) {
						maxitmap[i][k].setState(2);
						if (Mouse.isButtonDown(0)) {
							if (player1.getTurn() && !player2.getTurn()) {
								undoSelectable(spointx, spointy);
								maxitmap[i][k].setState(3);

								spointx = i;
								spointy = k;

								player1.addScore(maxitmap[i][k].getNumber());
								player1.setTurn(false);
								player2.setTurn(true);
							}

							else if (player2.getTurn() && !player1.getTurn()) {
								undoSelectable(spointx, spointy);
								maxitmap[i][k].setState(3);

								spointx = i;
								spointy = k;

								player2.addScore(maxitmap[i][k].getNumber());
								player2.setTurn(false);
								player1.setTurn(true);
							}

						}
					} else {
						maxitmap[i][k].setState(1);
					}
				}
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}

		if (mouseX > Game.width - 100 && mouseX < Game.width - 60
				&& mouseY < Game.height - 30 && mouseY > Game.height - 45) {
			exitcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				clickSound.play();
				Display.destroy();
				System.exit(0);
			}
		} else {
			exitcolor = Color.white;
		}

		if (mouseX > 40 && mouseX < 80 && mouseY < 70 && mouseY > 58) {
			backcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				clickSound.play();
				restart();
				sbg.enterState(0);
			}
		} else {
			backcolor = Color.white;
		}

	}

	@Override
	public int getID() {
		return 1;
	}

}
