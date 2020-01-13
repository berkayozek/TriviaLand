package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TriviaLand;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TriviaLand.WIDTH;
		config.height = TriviaLand.HEIGHT;
		config.addIcon("logoicon.png", Files.FileType.Internal);
		config.fullscreen = false;
		config.resizable = true;
		new LwjglApplication(new TriviaLand(), config);
	}
}
