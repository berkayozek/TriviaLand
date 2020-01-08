package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.TriviaLand;
import org.w3c.dom.Text;


import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class StartingScreen implements Screen {

    private TriviaLand game;
    private Music music;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextButtonStyle textButtonStyle;
    private Stage stage;
    private int playerNumber=1;
    private float fade = 0.7f;
    private Texture logoTexture;
    private ArrayList<Texture> textures = new ArrayList<>();
    private ArrayList<TextureRegion> texturesRegions = new ArrayList<>();
    private ArrayList<TextureRegionDrawable> texturesRegionsDrawable = new ArrayList<>();
    private Sprite logoSprite;
    private ArrayList<Button> buttons;
    public boolean StartingScreen = true;
    private OrthographicCamera camera;
    private Viewport viewport;

    public StartingScreen(TriviaLand game){
        this.game = game;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    @Override
    public void show() {
    batch = new SpriteBatch();
    stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    camera= new OrthographicCamera(TriviaLand.WIDTH, TriviaLand.HEIGHT);
    camera.setToOrtho(false, TriviaLand.WIDTH, TriviaLand.HEIGHT);
    viewport = new FitViewport(TriviaLand.WIDTH,TriviaLand.HEIGHT,camera);
    Gdx.input.setInputProcessor(stage);
    music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
    music.setLooping(true);
    music.setVolume(0.5f);
    music.play();
    font = new BitmapFont(Gdx.files.internal("font.fnt"));
    textButtonStyle = new TextButtonStyle();
    textButtonStyle.font = font;
    buttons = new ArrayList<>();
    textures.add(new Texture("buttons/startbutton.png"));
    textures.add(new Texture("buttons/exitbutton.png"));
    textures.add(new Texture("buttons/minus.png"));
    textures.add(new Texture("buttons/plus.png"));
    for (int i=0;i<textures.size();i++){
        textures.get(i).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texturesRegions.add(new TextureRegion(textures.get(i)));
        texturesRegionsDrawable.add(new TextureRegionDrawable(texturesRegions.get(i)));
        buttons.add(new ImageButton(texturesRegionsDrawable.get(i)));
        buttons.get(i).setTransform(true);
    }
    logoTexture = new Texture("logo.png");
    logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    logoSprite = new Sprite(logoTexture);
    logoSprite.setPosition(500,200);
    buttons.get(0).setPosition(300,375);
    buttons.get(1).setPosition(300,100);
    buttons.get(2).setPosition(700,100);
    buttons.get(3).setPosition(700,375);
    buttons.get(0).addListener(new ClickListener(){
        public void clicked(InputEvent event, float x, float y) {
            switchScreen(new whoStartFirstScreen(game,playerNumber));
        }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            buttons.get(0).addAction(Actions.alpha(fade,0.3f));
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            buttons.get(0).addAction(Actions.alpha(1f,0.3f));
        }
    });
    buttons.get(1).addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.exit(0);
        }
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            buttons.get(1).addAction(Actions.alpha(fade,0.3f));
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            buttons.get(1).addAction(Actions.alpha(1f,0.3f));
        }
    });
    buttons.get(2).addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if(playerNumber>1 && playerNumber<=4){
                playerNumber--;
            }
        }
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            buttons.get(2).addAction(Actions.alpha(fade,0.3f));
        }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            buttons.get(2).addAction(Actions.alpha(1f,0.3f));
        }
    });
    buttons.get(3).addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if(playerNumber>0 && playerNumber<4){
                playerNumber++;
            }
        }
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            buttons.get(3).addAction(Actions.alpha(fade,0.3f));
        }
        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            buttons.get(3).addAction(Actions.alpha(1f,0.3f));
        }
    });

    for (Button b : buttons)
        stage.addActor(b);
    stage.getRoot().getColor().a = 0;
    stage.getRoot().addAction(fadeIn(3f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(stage);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.setViewport(viewport);
        logoSprite.setPosition(350,350);
        logoSprite.setScale(1.25f);
        batch.begin();
            font.setColor(Color.BLACK);
            font.draw(batch,Integer.toString(playerNumber),700,300);
            logoSprite.draw(batch);
        batch.end();
        stage.draw();
        camera.update();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
