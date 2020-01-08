package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.mygdx.game.TriviaLand;

public class Options implements Screen {

    private TriviaLand game;

    public Options (TriviaLand game){
        this.game = game;
    }

    @Override
    public void show() {

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY ) && !Gdx.graphics.isFullscreen()){
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
        Gdx.graphics.setWindowedMode(1300,800);*/
    }

    @Override
    public void render(float delta) {

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
