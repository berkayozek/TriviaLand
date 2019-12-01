package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;

import java.util.Scanner;

public class TriviaLand extends Game {
	public static final int WIDTH = 920;
	public static final int HEIGHT = 800;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		//TODO Oyun Ekranları arasında geçiş buradan yapılacak
	}

	@Override
	public void render () {

		super.render();
	}

	@Override
	public void dispose () {

		batch.dispose();
	}
}
