package States;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Controller.Game;

public class Menu extends BasicGameState {
	float mouseX, mouseY;
	private Color spcolor, mpcolor, opcolor, crcolor, exitcolor;
	private String sptext, mptext, optext, crtext, exittext;
	private int buttonAlignX = 50, buttonAlignY = 250;

	public static Music menuMusic;
	private Sound clickSound;
	private Animation anime;
	private Image background;

	private int[] duration = { 75, 75, 75, 75, 75, 75, 75 };

	public Menu(int state) {

	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		
		background = new Image("/res/img/menu/bgmini3.png");

		spcolor = Color.white;
		mpcolor = Color.white;
		opcolor = Color.white;
		crcolor = Color.white;
		exitcolor = Color.white;

		sptext = "Singleplayer";
		mptext = "Multiplayer";
		optext = "Options";
		crtext = "Credits";
		exittext = "Exit";

		Image[] tip = { new Image("/res/img/menu/tip1.png"),
				new Image("/res/img/menu/tip2.png"), new Image("/res/img/menu/tip3.png"),
				new Image("/res/img/menu/tip4.png"), new Image("/res/img/menu/tip3.png"),
				new Image("/res/img/menu/tip2.png"), new Image("/res/img/menu/tip1.png"), };

		anime = new Animation(tip, duration, false);

		menuMusic = new Music("/res/sound/iloveyouso.ogg");
		menuMusic.loop(1, 0.04f);

		clickSound = new Sound("/res/sound/clickSound.wav");

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);

		g.setColor(spcolor);
		g.drawString(sptext, buttonAlignX, Game.height - buttonAlignY);
		
		g.setColor(mpcolor);
		g.drawString(mptext, buttonAlignX, Game.height - buttonAlignY + 40);
		
		g.setColor(opcolor);
		g.drawString(optext, buttonAlignX, Game.height - buttonAlignY + 80);
		
		g.setColor(crcolor);
		g.drawString(crtext, buttonAlignX, Game.height - buttonAlignY + 120);
		
		g.setColor(exitcolor);
		g.drawString(exittext, buttonAlignX, Game.height - buttonAlignY + 160);

		g.setColor(Color.cyan);
		g.drawString("Kitten Maxit", (Game.width / 2) - 70, 20);

		anime.draw(1000, 650);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {

		// Screen Update
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();

		anime.update(delta);

		// Input Collection

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}

		if ((mouseX > buttonAlignX && mouseX < buttonAlignX + 110)
				&& (mouseY > buttonAlignY - 20) && mouseY < buttonAlignY) {
			spcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(1);
				clickSound.play();
			}
		} else {
			spcolor = Color.white;
		}

		if ((mouseX > buttonAlignX && mouseX < buttonAlignX + 100)
				&& (mouseY > buttonAlignY - 60) && mouseY < buttonAlignY - 40) {
			mpcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(6);
				clickSound.play();
			}
		} else {
			mpcolor = Color.white;
		}

		if ((mouseX > buttonAlignX && mouseX < buttonAlignX + 65)
				&& (mouseY > buttonAlignY - 100) && mouseY < buttonAlignY - 80) {
			opcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(2);
				clickSound.play();
			}
		} else {
			opcolor = Color.white;
		}

		if ((mouseX > buttonAlignX && mouseX < buttonAlignX + 65)
				&& (mouseY > buttonAlignY + -140)
				&& mouseY < buttonAlignY - 120) {
			crcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(3);
				clickSound.play();
			}
		} else {
			crcolor = Color.white;
		}

		if ((mouseX > buttonAlignX && mouseX < buttonAlignX + 40)
				&& (mouseY > buttonAlignY - 180) && mouseY < buttonAlignY - 160) {
			exitcolor = Color.red;
			if (Mouse.isButtonDown(0)) {
				clickSound.play();
				Display.destroy();
				System.exit(0);
			}
		} else {
			exitcolor = Color.white;
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
