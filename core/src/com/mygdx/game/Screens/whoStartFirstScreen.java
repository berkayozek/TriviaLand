package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
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

    private Die die = new Die();

    private TextButtonStyle textButtonStyle;





    private TextButton Roll;
    private TextButton letsPlay;

    private int playerCount = 0;
    private int PlayerNumber = 0;
    private int d = 0;
    ArrayList<User> userArrayList=new ArrayList<User>();
    private ArrayList<Integer>dies=new ArrayList<Integer>();
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
        stage = new Stage(new ExtendViewport(800, 920));
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        Roll=new TextButton("Roll",textButtonStyle);
        letsPlay=new TextButton("Let's Play",textButtonStyle);
        Roll.setPosition(420,360);
        letsPlay.setPosition(420,160);
        Roll.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (playerCount<PlayerNumber) {
                    die.roll();
                    d = die.getDie1();
                   // System.out.println(d);
                    dies.add(d);
                    playerCount++;
                }
                if(playerCount==PlayerNumber){
                    whoStartFirst();
                    stage.addActor(letsPlay);
                }
            }


        });
        letsPlay.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {


                switchScreen(new PlayScreen(game,userArrayList));
            }
        });

        stage.addActor(Roll);
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

            font.draw(batch,"Who Play First?",200,600);


           if(playerCount>0) {
               font.draw(batch,"Player " + playerCount,200,500);
           }
            font.draw(batch,Integer.toString(d),700,300);

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
