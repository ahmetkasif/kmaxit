package com.codeaia.maxit.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codeaia.maxit.controller.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.samples = 4; 
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		config.useGL30 = true;
		config.resizable = false;
		config.addIcon("img/codeaia32.png", Files.FileType.Internal);
		config.vSyncEnabled = true;
		new LwjglApplication(new Game(), config);
	}
}
