package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.Die;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.TriviaLand;


import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class whoStartFirstScreen implements Screen {
    private TriviaLand game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Die die;
    private TextButtonStyle textButtonStyle;
    private StartingScreen s;
    private TextButton Roll;
    private ArrayList<Integer>dies=new ArrayList<Integer>();
    public whoStartFirstScreen(TriviaLand game){
        this.game=game;
    }

    public int whoStartFirst(){
        int a=0;
        int numOfRoll=s.getPlayerNumber();
        while(numOfRoll>0){
            die.roll();
           dies.add(die.getDie1());
            numOfRoll--;


        }
        a=numOfRoll;
        while(numOfRoll<dies.size()-1){
            if(dies.get(numOfRoll)<dies.get(numOfRoll+1)){
                a=numOfRoll+1;
            }

        }
        return a;
    }

    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(800, 920));
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        textButtonStyle = new TextButtonStyle();

        textButtonStyle.font = font;
        Roll=new TextButton("Roll",textButtonStyle);
        Roll.setPosition(420,460);
        Roll.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }

            d
        });


        stage.addActor(Roll);
    }

    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());

        batch.begin();
            stage.draw();
        batch.end();
        batch.begin();
            font.draw(batch,"Who Play First?",200,600);

        batch.end();
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
