package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Board;
import com.mygdx.game.Die;
import com.mygdx.game.TriviaLand;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {

    private Board b1 = new Board();
    private TriviaLand game;
    private Sprite splash;
    private SpriteBatch batch;
    private Die die;
    ShapeRenderer shape = new ShapeRenderer();
    private float hatY = 0;
    private float hatX = 8;
    private float move = 8;
    private BitmapFont font;
    private boolean isDie = false;

    public PlayScreen(TriviaLand game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        die = new Die();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        Texture texture = new Texture("Hat.png");
        splash = new Sprite(texture);
        splash.setSize(50,50);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        // Üst ve alt
        for (int k = 0; k < 9; k++)
            for (int i = 0; i < 9; i++)
                if (b1.getBoard(k, i) > 1 && b1.getBoard(k, i) < 10 || b1.getBoard(k, i) > 17 && b1.getBoard(k, i) < 25)
                    shape.rect((int) (k * 74 + 129), (int) (i * 77.625 + 40), 70, 99);

        // sol ve sağ
        for (int k = 0; k < 9; k++)
            for (int i = 0; i < 9; i++)
                if (b1.getBoard(k, i) > 25 && b1.getBoard(k, i) <= 32 || b1.getBoard(k, i) > 9 && b1.getBoard(k, i) < 17)
                    shape.rect((int) (k * 77.625 + 100), (int) (i * 74 + 69), 99, 70);

        for (int k = 0; k < 9; k++)
            for (int i = 0; i < 9; i++)
                if (b1.getBoard(k, i) == 1 || b1.getBoard(k, i) == 9 || b1.getBoard(k, i) == 17
                        || b1.getBoard(k, i) == 25)
                    shape.rect((int) (k * 77.625 + 100), (int) (i * 77.625 + 40), 99, 99);


        //Zar butonu
        shape.setColor(Color.RED);
        shape.rect(400,400,140,80);
        shape.end();
        splash.setCenter((int) (hatX * 77.625 + 130), (int) (hatY * 74 + 79));


        batch.begin();
        splash.draw(batch);
        font.draw(batch,"Zar",420,460);
        if (isDie)
            font.draw(batch,String.valueOf(die.getDie1()),300,250);
        batch.end();

        if (hatX-move>0.1f){
            hatX-=1f*delta;
        }

        if (Gdx.input.isTouched())
            System.out.println(Gdx.input.getX() + "  " + Gdx.input.getY());

        //if (Gdx.input.getX())
        if (Gdx.input.isTouched() && (Gdx.input.getX()>400 && Gdx.input.getX()<540 && Gdx.input.getY()>320 && Gdx.input.getY()<400)){
            //move = (float) ((Gdx.input.getX()-130)/77.625);
            die.roll();
            move = (float) die.getDie1();
            isDie = true;
            System.out.println("Dokundun " + Gdx.input.getX() + " " + die.getDie1() );

        }

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
