package com.codeaia.maxit.controller;

public class Constants { public static int width;
  public static int height;
  public static float scale;
  public static boolean fullscreen;
  public static boolean vsync;
  public static boolean fpsEnable;
  public static float soundVolume;
  public static float musicVolume;
  public static String playerName;
  
  public Constants() {}
  
  public static void loadConstants() { width = Game.getPrefs().getInteger("width");
    height = Game.getPrefs().getInteger("height");
    scale = Game.getPrefs().getFloat("scale");
    fullscreen = Game.getPrefs().getBoolean("fullscreen");
    vsync = Game.getPrefs().getBoolean("vsync");
    fpsEnable = Game.getPrefs().getBoolean("fpsEnable");
    soundVolume = Game.getPrefs().getFloat("soundVolume");
    musicVolume = Game.getPrefs().getFloat("musicVolume");
    playerName = Game.getPrefs().getString("playerName");
  }
}