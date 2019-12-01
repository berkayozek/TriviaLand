package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.TriviaLand;

public class StartingScreen implements Screen {

    TriviaLand game;

    public StartingScreen(TriviaLand game){
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //TODO Starting Scenes yapılarak ana menü ve oyun arasında geçişler sağlanacak
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
