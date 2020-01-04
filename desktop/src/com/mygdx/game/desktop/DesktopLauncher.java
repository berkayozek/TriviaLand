package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TriviaLand;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TriviaLand.WIDTH;
		config.height = TriviaLand.HEIGHT;
		config.resizable = true;
		new LwjglApplication(new TriviaLand(), config);
	}
}
