package Controller;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import States.Audio;
import States.Credits;
import States.Graphics;
import States.Level1;
import States.Menu;
import States.Options;
import States.Multiplayer;

public class Game extends StateBasedGame {
	public static final String name = "Kitten Maxit";
	public static final int width = 1366, height = 768;
	public static int menu = 0, level1 = 1, options = 2, credits = 3, graphics = 4, audio = 5, multiplayer = 6;

	public Game(String name) {
		super(name);
		
		this.addState(new Menu(menu));
		this.addState(new Level1(level1));
		this.addState(new Options(options));
		this.addState(new Credits(credits));
		this.addState(new Graphics(graphics));
		this.addState(new Audio(audio));
		this.addState(new Multiplayer(multiplayer));
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(menu).init(container, this);
		this.getState(level1).init(container, this);
		this.getState(options).init(container, this);
		this.getState(credits).init(container, this);
		this.getState(graphics).init(container, this);
		this.getState(audio).init(container, this);
		this.getState(multiplayer).init(container, this);

		this.enterState(menu);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app;
		app = new AppGameContainer(new Game(name), width, height, true);
		app.setVSync(true);
		app.start();
		
	}
	
}
