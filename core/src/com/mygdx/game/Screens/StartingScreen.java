package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.User;
import com.mygdx.game.TriviaLand;
import org.w3c.dom.Text;


import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class StartingScreen implements Screen {

    private TriviaLand game;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextButton start;
    private TextButton exit;
    private TextButtonStyle textButtonStyle;
    private Stage stage;
    private Button increaseButton;
    private Button decreaseButton;
    private int playerNumber=1;
    private Texture logoTexture,startImage;
    private ArrayList<Texture> textures = new ArrayList<>();
    private ArrayList<TextureRegion> texturesRegions = new ArrayList<>();
    private ArrayList<TextureRegionDrawable> texturesRegionsDrawable = new ArrayList<>();
    private TextureRegion startRegion;
    private TextureRegionDrawable startDrawable;
    private Sprite logoSprite;
    private ArrayList<Button> buttons;
    public boolean StartingScreen = true;

    public StartingScreen(TriviaLand game){
        this.game = game;
    }

    public int getPlayerNumber(){
        return playerNumber;
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
    decreaseButton=new TextButton("-",textButtonStyle);
    increaseButton=new TextButton("+",textButtonStyle);
    buttons = new ArrayList<>();
    textures.add(new Texture("buttons/startbutton.png"));
    textures.add(new Texture("buttons/exitbutton.png"));
    for (int i=0;i<textures.size();i++){
        textures.get(i).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texturesRegions.add(new TextureRegion(textures.get(i)));
        texturesRegionsDrawable.add(new TextureRegionDrawable(texturesRegions.get(i)));
        buttons.add(new ImageButton(texturesRegionsDrawable.get(i)));
    }
    logoTexture = new Texture("logo.png");
    logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    logoSprite = new Sprite(logoTexture);
    logoSprite.setPosition(500,200);
    buttons.get(0).setPosition(300,450);
    buttons.get(1).setPosition(300,150);
    start.setPosition(300,450);
    exit.setPosition(300,150);
    increaseButton.setPosition(800,450);
    decreaseButton.setPosition(800,150);
    increaseButton.addListener(new ClickListener(){
        public void clicked(InputEvent event, float x,float y){
            if(playerNumber>0 && playerNumber<4){
                playerNumber++;

            }
        }
        });
    decreaseButton.addListener(new ClickListener(){
          public void clicked(InputEvent event, float x,float y){
              if(playerNumber>1 && playerNumber<=4){
                  playerNumber--;

              }
          }
        });
    start.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {


            switchScreen(new whoStartFirstScreen(game,playerNumber));
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
    stage.addActor(increaseButton);
    stage.addActor(decreaseButton);
    for (Button b : buttons)
        stage.addActor(b);
    stage.getRoot().getColor().a = 0;
    stage.getRoot().addAction(fadeIn(3f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        logoSprite.setPosition(350,350);
        logoSprite.setScale(1.25f);
        batch.begin();
            font.draw(batch,Integer.toString(playerNumber),700,300);
            logoSprite.draw(batch);
        batch.end();
        stage.draw();

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
