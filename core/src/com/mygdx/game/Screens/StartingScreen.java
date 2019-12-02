package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.TriviaLand;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class StartingScreen implements Screen {

    private TriviaLand game;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextButton start;
    private TextButton exit;
    private TextButtonStyle textButtonStyle;
    private Stage stage;


    public boolean StartingScreen = true;

    public StartingScreen(TriviaLand game){
        this.game = game;
    }

    @Override
    public void show() {
    batch = new SpriteBatch();
    stage = new Stage(new ExtendViewport(800, 920));
    Gdx.input.setInputProcessor(stage);
    font = new BitmapFont(Gdx.files.internal("font.fnt"));
    textButtonStyle = new TextButtonStyle();
    textButtonStyle.font = font;
    start = new TextButton("START",textButtonStyle);
    exit = new TextButton("EXIT",textButtonStyle);
    start.setPosition(300,450);
    exit.setPosition(300,150);
    start.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            switchScreen(new PlayScreen(game));
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            start.getLabel().setColor(new Color(255,255,255, (float) 0.50));
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            start.getLabel().setColor(new Color(255,255,255,1));
        }
    });
    exit.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.exit(0);
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            exit.getLabel().setColor(new Color(255,255,255, (float) 0.50));
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            exit.getLabel().setColor(new Color(255,255,255,1));
        }
    });
    stage.addActor(start);
    stage.addActor(exit);
    stage.getRoot().getColor().a = 0;
    stage.getRoot().addAction(fadeIn(3f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        batch.begin();
            stage.draw();
        batch.end();

        batch.begin();
            font.draw(batch,"TriviaLand",280,600);
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

    public void switchScreen( final Screen newScreen){
        stage.getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(fadeOut(0.5f));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(newScreen);
            }
        }));
        stage.getRoot().addAction(sequenceAction);
    }
}
