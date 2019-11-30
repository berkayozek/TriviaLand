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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.*;
import com.mygdx.game.TriviaLand;

public class PlayScreen implements Screen {

    private Board b1 = new Board();
    private TriviaLand game;
    private Sprite splash;
    private SpriteBatch batch;
    private Die die;
    ShapeRenderer shape = new ShapeRenderer();
    private User user;
    private float jumpVariable = 0;
    private boolean isJump = true;
    private BitmapFont font;
    private boolean isDie = false;
    private TextButton button;
    private TextButtonStyle style;
    private Stage stage;
    private float count = 0;

    public PlayScreen(TriviaLand game){
        this.game = game;
        user = new User("Player 1",false);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        die = new Die();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        Texture texture = new Texture("Hat.png");
        splash = new Sprite(texture);
        splash.setSize(50,50);
        style = new TextButtonStyle();
        style.font = font;
        stage = new Stage(new ExtendViewport(800, 920));
        Gdx.input.setInputProcessor(stage);
        button = new TextButton("Zar At",style);
        button.setPosition(420,460);
        button.addListener(new ClickListener(){
            public void clicked(InputEvent e,float x, float y){
                if (user.getMove()==user.getMoveCount()) {
                    die.roll();
                    isDie = true;
                    user.setMove(user.getMove() + die.getDie1());
                }
            }
        });

        stage.addActor(button);
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        shape.end();
        splash.setCenter((int)(user.getUserX()*77.625+150), (int)(user.getUserY()*74+99) + jumpVariable);


        //User Zıplama Efekti
        if (isJump) {
            jumpVariable += 5f*delta;
            if (jumpVariable>200f*delta)
                isJump = false;
        }
        else {
            jumpVariable -= 5f*delta;
            if (jumpVariable<0)
                isJump = true;
        }


        batch.begin();
        splash.draw(batch);
        if (isDie)
            font.draw(batch,String.valueOf(die.getDie1()),300,250);
        batch.end();



        //gerideyse
        if (user.getMoveCount()>user.getMove()) {
            if (count>1) {
                if (user.getUserX() >= 0 && user.getUserX() < 8 && user.getUserY() == 0) {
                    user.setUserX(user.getUserX() + 1);
                } else if (user.getUserY() >= 0 && user.getUserY() < 8 && user.getUserX() == 8) {
                    user.setUserY(user.getUserY() + 1);
                } else if (user.getUserX() > 0 && user.getUserX() <= 8 && user.getUserY() == 8) {
                    user.setUserX(user.getUserX() - 1);
                } else if (user.getUserY() > 0 && user.getUserY() <= 8 && user.getUserX() == 0) {
                    user.setUserY(user.getUserY() - 1);
                } else if (user.getUserX() > 0 && user.getUserX() <= 8 && user.getUserY() == 0) {
                    user.setUserX(user.getUserX() + 1);
                }

            }
        }

        count += delta;
        //Move Functions
        if (count>0.5) {
            if (user.getUserX() > 0 && user.getUserX() <= 8 && user.getUserY() == 0 && user.getMove() > user.getMoveCount()) {
                user.setUserX(user.getUserX() - 1);
                user.setMoveCount(user.getMoveCount() + 1);
                user.isDrawable = true;
            } else if (user.getUserY() >= 0 && user.getUserY() < 8 && user.getUserX() == 0 && user.getMove() > user.getMoveCount()) {
                user.setUserY(user.getUserY() + 1);
                user.setMoveCount(user.getMoveCount() + 1);
                user.isDrawable = true;
            } else if (user.getUserX() >= 0 && user.getUserX() < 8 && user.getUserY() == 8 && user.getMove() > user.getMoveCount()) {
                user.setUserX(user.getUserX() + 1);
                user.setMoveCount(user.getMoveCount() + 1);
                user.isDrawable = true;
            } else if (user.getUserY() > 0 && user.getUserY() <= 8 && user.getUserX() == 8 && user.getMove() > user.getMoveCount()) {
                user.setUserY(user.getUserY() - 1);
                user.setMoveCount(user.getMoveCount() + 1);
                user.isDrawable = true;
            }
            count = 0;
        }

        if (user.getUserX() == 8 && user.getUserY() == 8 && user.getMove()<user.getMoveCount())
            user.setMoveCount(1);

        //substracting value for preventing big number
        if (user.getMoveCount()>32 && user.getMove()>32 ) {
            user.setMoveCount(user.getMove()-32);
            user.setMove(user.getMove()-32);
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
