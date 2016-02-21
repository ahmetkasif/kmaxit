package com.codeaia.maxit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codeaia.maxit.controller.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1366;
		config.height = 768;
		config.fullscreen = true;
		config.samples = 4; 
		config.vSyncEnabled = false;
		new LwjglApplication(new Game(), config);
	}
}
