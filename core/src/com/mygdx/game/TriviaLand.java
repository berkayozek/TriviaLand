package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Screens.StartingScreen;
import com.mygdx.game.Screens.whoStartFirstScreen;

import java.util.Scanner;

public class TriviaLand extends Game {
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 800;
	public SpriteBatch batch;
	public static Music music;


	@Override
	public void create () {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		setScreen(new StartingScreen(this));
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
