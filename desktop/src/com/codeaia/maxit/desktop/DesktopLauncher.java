package com.codeaia.maxit.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.codeaia.maxit.controller.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Maxit+");
		config.setWindowedMode(1280, 720);
		config.useVsync(true);
		config.useOpenGL3(true, 3, 2);
		config.setResizable(false);
		config.setWindowPosition(100, 48);
		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 4);
		
		new Lwjgl3Application(new Game(), config);
	}
}
