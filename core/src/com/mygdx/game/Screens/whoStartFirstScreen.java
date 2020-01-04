package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game.Die;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.Game.User;
import com.mygdx.game.TriviaLand;


import java.util.ArrayList;
import java.util.Collections;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class whoStartFirstScreen implements Screen {
    private TriviaLand game;

    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Texture logoTexture,startImage;
    private ArrayList<Texture> textures = new ArrayList<>();
    private ArrayList<TextureRegion> texturesRegions = new ArrayList<>();
    private ArrayList<TextureRegionDrawable> texturesRegionsDrawable = new ArrayList<>();
    private ArrayList<Button> buttons= new ArrayList<>();
    private Die die = new Die();
    private Boolean isClickEarly;
    private TextButtonStyle textButtonStyle;
    private TextButton Roll;
    private TextButton letsPlay;
    private int playerCount = 0,PlayerNumber = 0,d = 0;
    private Label label;
    private Label.LabelStyle labelStyle;
    private ArrayList<User> userArrayList=new ArrayList<User>();
    private ArrayList<Integer>dies=new ArrayList<Integer>();
    private OrthographicCamera camera;
    private Viewport viewport;
    public whoStartFirstScreen(TriviaLand game,int i){
        this.game=game;
        int p=1;
      PlayerNumber=i;
        while (i>0) {
            userArrayList.add(new User("Player "+Integer.toString(p), false));
            i--;
            p++;

        }
    }

    public ArrayList<User> whoStartFirst(){
        int a=1;
        int n = dies.size();
        for (int i=0; i<n; ++i)
        {
            User u=userArrayList.get(i);
            u.setNumber(a);
            int key = dies.get(i);
            int j = i-1;

            while (j>=0 && dies.get(j) > key)
            {
                userArrayList.set(j+1,userArrayList.get(j));
                j = j-1;
            }
            userArrayList.set(j+1,u);
           a++;
        }
        Collections.reverse(userArrayList);

       for(int i=0;i<userArrayList.size();i++){
           System.out.println(userArrayList.get(i).getName());
       }
        return userArrayList;
    }

    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(TriviaLand.WIDTH, TriviaLand.HEIGHT));
        camera= new OrthographicCamera(TriviaLand.WIDTH,TriviaLand.HEIGHT);
        camera.setToOrtho(false, TriviaLand.WIDTH, TriviaLand.HEIGHT);
        viewport = new FitViewport(TriviaLand.WIDTH,TriviaLand.HEIGHT,camera);
        isClickEarly = false;
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        label = new Label("Berkay",labelStyle);
        label.setAlignment(Align.center);
        label.setPosition(0,800);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        Roll=new TextButton("Roll",textButtonStyle);
        letsPlay=new TextButton("Let's Play",textButtonStyle);
        textures.add(new Texture("buttons/roll.png"));
        textures.add(new Texture("buttons/letsplay.png"));
        textures.add(new Texture("Tokens/Hat.png"));
        textures.add(new Texture("Tokens/Car.png"));
        textures.add(new Texture("Tokens/Dog.png"));
        textures.add(new Texture("Tokens/Ship.png"));
        textures.add(new Texture("Tokens/Shoes.png"));
        textures.add(new Texture("Tokens/Something.png"));
        textures.add(new Texture("Tokens/Something2.png"));
        Roll.setPosition(420,360);
        letsPlay.setPosition(420,160);
        for (int i=0;i<textures.size();i++){
            textures.get(i).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            texturesRegions.add(new TextureRegion(textures.get(i)));
            texturesRegionsDrawable.add(new TextureRegionDrawable(texturesRegions.get(i)));
            buttons.add(new ImageButton(texturesRegionsDrawable.get(i)));
        }
        for (int i=2;i<buttons.size();i++){
            buttons.get(i).setSize(100,75);
            buttons.get(i).setPosition(0+(i*125),100);
        }
        buttons.get(0).setPosition(400,370);
        buttons.get(1).setPosition(420,160);
        buttons.get(0).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!userArrayList.get(playerCount).getLocation().equals("")) {
                    if (playerCount < PlayerNumber) {
                        die.roll();
                        d = die.getDie1();
                        // System.out.println(d);
                        dies.add(d);
                        playerCount++;
                    }
                    if (playerCount == PlayerNumber) {
                        whoStartFirst();
                        buttons.get(1).setVisible(true);
                    }
                    isClickEarly = false;
                }
                else
                    isClickEarly = true;
            }
        });
        buttons.get(1).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                    switchScreen(new PlayScreen(game,userArrayList));
            }
        });

        buttons.get(2).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(2).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Hat.png");
                }
            }
        });
        buttons.get(3).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(3).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Car.png");
                }
            }
        });
        buttons.get(4).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(4).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Dog.png");
                }
            }
        });
        buttons.get(5).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(5).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Ship.png");
                }
            }
        });
        buttons.get(6).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(6).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Shoes.png");
                }
            }
        });
        buttons.get(7).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(7).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Something.png");
                }
            }
        });
        buttons.get(8).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(8).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Something2.png");
                }
            }
        });

        for (Button b : buttons)
            stage.addActor(b);
        buttons.get(1).setVisible(false);
        stage.addActor(label);
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(3f));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.setViewport(viewport);
        batch.begin();
        stage.draw();
        font.setColor(Color.BLACK);
        batch.end();
        batch.begin();
        font.draw(batch,"Who Play First?",200,600);
        if(isClickEarly)
            font.draw(batch,"Please Select Tokens First",200,500);
        if(playerCount>0)
            font.draw(batch,"Player " + playerCount,200,700);
        font.draw(batch,Integer.toString(d),700,300);
        batch.end();

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
