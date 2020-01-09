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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
    private ArrayList<Texture> textures = new ArrayList<>();
    private ArrayList<TextureRegion> texturesRegions = new ArrayList<>();
    private ArrayList<TextureRegionDrawable> texturesRegionsDrawable = new ArrayList<>();
    private ArrayList<Button> buttons= new ArrayList<>();
    private Die die = new Die();
    private Boolean isClickEarly;
    private TextButtonStyle textButtonStyle;
    private StatustStage stages = StatustStage.selectBot;
    private int playerCount = 0,PlayerNumber = 0,d = 0,botNumber = 0;
    private ArrayList<String> locationArr = new ArrayList<>();
    private Label label;
    private Label.LabelStyle labelStyle;
    private ArrayList<User> userArrayList=new ArrayList<User>();
    private ArrayList<Integer>dies=new ArrayList<Integer>();
    private OrthographicCamera camera;
    private Viewport viewport;

    enum StatustStage{
        selectBot,
        select;
    };
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
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;

        locationArr.add("Tokens/Hat.png");
        locationArr.add("Tokens/Car.png");
        locationArr.add("Tokens/Dog.png");
        locationArr.add("Tokens/Ship.png");
        locationArr.add("Tokens/Shoes.png");
        locationArr.add("Tokens/Something.png");
        locationArr.add("Tokens/Something2.png");
        textures.add(new Texture("buttons/roll.png"));
        textures.add(new Texture("buttons/letsplay.png"));
        textures.add(new Texture("Tokens/Hat.png"));
        textures.add(new Texture("Tokens/Car.png"));
        textures.add(new Texture("Tokens/Dog.png"));
        textures.add(new Texture("Tokens/Ship.png"));
        textures.add(new Texture("Tokens/Shoes.png"));
        textures.add(new Texture("Tokens/Something.png"));
        textures.add(new Texture("Tokens/Something2.png"));
        textures.add(new Texture("buttons/plus.png"));
        textures.add(new Texture("buttons/minus.png"));
        textures.add(new Texture("buttons/addbot.png"));
        textures.add(new Texture("buttons/volumeoff.png"));
        textures.add(new Texture("buttons/volumeon.png"));
        textures.add(new Texture("buttons/fullscreen.png"));
        textures.add(new Texture("buttons/exitfullscreen.png"));

        for (int i=0;i<textures.size();i++){
            textures.get(i).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            texturesRegions.add(new TextureRegion(textures.get(i)));
            texturesRegionsDrawable.add(new TextureRegionDrawable(texturesRegions.get(i)));
            buttons.add(new ImageButton(texturesRegionsDrawable.get(i)));
        }
        for (int i=2;i<buttons.size()-7;i++){
            buttons.get(i).setSize(100,75);
            buttons.get(i).setPosition(0+(i*125),100);
            buttons.get(i).setVisible(false);
        }
        for (int i=12;i<14;i++) {
            buttons.get(i).setPosition(1220, 720);
            buttons.get(i).setSize(50,50);
            buttons.get(i).setVisible(false);
        }
        for (int i=14;i<16;i++){
            buttons.get(i).setPosition(1170,720);
            buttons.get(i).setSize(50,50);
            buttons.get(i).setVisible(false);
        }

        if (TriviaLand.music.getVolume()==0)
            buttons.get(13).setVisible(true);
        else
            buttons.get(12).setVisible(true);
        if (Gdx.graphics.isFullscreen())
            buttons.get(15).setVisible(true);
        else
            buttons.get(14).setVisible(true);

        buttons.get(12).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TriviaLand.music.setVolume(0f);
                buttons.get(12).setVisible(false);
                buttons.get(13).setVisible(true);
            }
        });

        buttons.get(13).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TriviaLand.music.setVolume(0.5f);
                buttons.get(13).setVisible(false);
                buttons.get(12).setVisible(true);
            }
        });

        buttons.get(14).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                buttons.get(14).setVisible(false);
                buttons.get(15).setVisible(true);
            }
        });

        buttons.get(15).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.graphics.setWindowedMode(1300,800);
                buttons.get(15).setVisible(false);
                buttons.get(14).setVisible(true);
            }
        });

        buttons.get(9).setPosition(400,370);
        buttons.get(10).setPosition(400,160);
        buttons.get(11).setPosition(550,160);
        buttons.get(0).setPosition(400,370);
        buttons.get(0).setVisible(false);
        buttons.get(1).setPosition(420,160);
        buttons.get(1).setVisible(false);
        buttons.get(0).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!userArrayList.get(playerCount).getLocation().equals("")) {
                    if (playerCount < PlayerNumber-botNumber) {
                        die.roll();
                        d = die.getDie1();
                        // System.out.println(d);
                        dies.add(d);
                        playerCount++;
                    }
                    if (playerCount == PlayerNumber-botNumber) {
                        for (int i=0;i<botNumber;i++)
                            dies.add((int)(Math.random()*6)+1);
                        whoStartFirst();
                        buttons.get(1).setVisible(true);
                    }
                    isClickEarly = false;
                }
                else
                    isClickEarly = true;
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttons.get(0).addAction(Actions.alpha(0.7f, 0.3f));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttons.get(0).addAction(Actions.alpha(1f, 0.3f));
            }
        });
        buttons.get(1).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                    switchScreen(new PlayScreen(game,userArrayList));
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttons.get(1).addAction(Actions.alpha(0.7f, 0.3f));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttons.get(1).addAction(Actions.alpha(1f, 0.3f));
            }
        });

        buttons.get(2).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(2).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Hat.png");
                    locationArr.remove(0);
                }
            }
        });
        buttons.get(3).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(3).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Car.png");
                    locationArr.remove(1);
                }
            }
        });
        buttons.get(4).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(4).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Dog.png");
                    locationArr.remove(2);

                }
            }
        });
        buttons.get(5).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(5).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Ship.png");
                    locationArr.remove(3);

                }
            }
        });
        buttons.get(6).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(6).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Shoes.png");
                    locationArr.remove(4);

                }
            }
        });
        buttons.get(7).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(7).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Something.png");
                    locationArr.remove(5);
                }
            }
        });
        buttons.get(8).addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (userArrayList.get(playerCount).getLocation().equals("")) {
                    buttons.get(8).setVisible(false);
                    userArrayList.get(playerCount).setLocation("Tokens/Something2.png");
                    locationArr.remove(6);
                }
            }
        });

        buttons.get(9).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (PlayerNumber!=botNumber)
                    botNumber++;
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttons.get(9).addAction(Actions.alpha(0.7f, 0.3f));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttons.get(9).addAction(Actions.alpha(1f, 0.3f));
            }
        });

        buttons.get(10).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (botNumber>=1)
                    botNumber--;
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttons.get(10).addAction(Actions.alpha(0.7f, 0.3f));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttons.get(10).addAction(Actions.alpha(1f, 0.3f));
            }
        });

        buttons.get(11).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stages = StatustStage.select;
                for (int i=0;i<buttons.size()-3;i++)
                    if (i!=1)
                        buttons.get(i).setVisible(true);
                buttons.get(9).setVisible(false);
                buttons.get(10).setVisible(false);
                buttons.get(11).setVisible(false);
                for (int i=PlayerNumber-botNumber;i<PlayerNumber;i++) {
                    userArrayList.set(i, new User("Player " + Integer.toString(i+1), true));
                    System.out.println("aa" +i + " ");
                    userArrayList.get(i).setLocation(locationArr.get((int)(Math.random()*7)));
                }
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                buttons.get(11).addAction(Actions.alpha(0.7f, 0.3f));
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                buttons.get(11).addAction(Actions.alpha(1f, 0.3f));
            }
        });

        for (Button b : buttons) {
            stage.addActor(b);
        }
        buttons.get(1).setVisible(false);
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


        if (stages == StatustStage.selectBot)
            font.draw(batch,Integer.toString(botNumber),700,300);
        else if (stages == StatustStage.select){
            font.draw(batch,"Who Play First?",200,600);
            if(isClickEarly)
                font.draw(batch,"Please Select Tokens First",200,500);
            if(playerCount>0)
                font.draw(batch,"Player " + playerCount,200,700);
            font.draw(batch,Integer.toString(d),700,300);
        }

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
