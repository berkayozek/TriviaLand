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
import com.badlogic.gdx.scenes.scene2d.Actor;
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

import java.util.ArrayList;

public class PlayScreen implements Screen {

    private Board b1 = new Board();
    private Cities cities = new Cities();
    private TriviaLand game;
    private Sprite splash;
    private ArrayList<Sprite> citiesSprite;
    private SpriteBatch batch;
    private ArrayList<Texture> citiesImage;
    private Die die;
    ShapeRenderer shape = new ShapeRenderer();
    private User user;
    private User newUser;
    private float jumpVariable = 0;
    private boolean isJump = true;
    private BitmapFont font;
    private boolean isDie = false;
    private TextButton button;
    private TextButtonStyle style;
    private Stage stage;
    private float fontsize = 1f;
    private boolean isHoover = false;
    private CardDeck cards=new CardDeck();
    private float userPosCouter = 0;
    private float speed = 50f;
    private ArrayList<User> usersArray=new ArrayList<User>();
    private Card c;

    public PlayScreen(TriviaLand game,ArrayList<User> users ) {
        this.game = game;
        usersArray=users;
        user = new User("berkay",false);
    }



    @Override
    public void show() {
        System.out.println(usersArray.size());
        batch = new SpriteBatch();
        die = new Die();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        Texture texture = new Texture("Hat.png");
        splash = new Sprite(texture);
        splash.setSize(35, 35);
        style = new TextButtonStyle();
        style.font = font;
        stage = new Stage(new ExtendViewport(800, 920));
        Gdx.input.setInputProcessor(stage);
        button = new TextButton("Zar At", style);
        button.setPosition(420, 460);
        button.setTransform(true);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (user.getMove() == user.getMoveCount()) {
                    die.roll();
                    isDie = true;
                    user.setMove(user.getMove() + die.getDie1());
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                isHoover = true;
                button.getLabel().setColor(Color.SALMON);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                isHoover = false;
                button.getLabel().setColor(Color.WHITE);
            }
        });

        citiesImage = new ArrayList<>();
        citiesSprite = new ArrayList<>();

        citiesImage.add(new Texture("Istanbul.png"));
        for (int i = 0; i < 7; i++) {
            citiesSprite.add(new Sprite(citiesImage.get(0)));
            citiesSprite.get(i).setSize(70, 99);
        }
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
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


        shape.end();
        splash.setCenter(user.getUserPos().x, user.getUserPos().y + jumpVariable);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        for (int i = 0; i < citiesSprite.size(); i++)
            citiesSprite.get(i).setCenter((int) ((i + 2) * 74 + 90), (int) (0 * 74 + 90));

        //User Zıplama Efekti
        if (isJump) {
            jumpVariable += 5f * delta;
            if (jumpVariable > 200f * delta)
                isJump = false;
        } else {
            jumpVariable -= 5f * delta;
            if (jumpVariable < 0)
                isJump = true;
        }


        batch.begin();
        for (int i = 0; i < citiesSprite.size(); i++)
            citiesSprite.get(i).draw(batch);
        splash.draw(batch);

        font.setColor(Color.WHITE);
        font.getData().setScale(1f,1f);

        if (isDie)
            font.draw(batch, String.valueOf(die.getDie1()), 300, 250);

        font.setColor(Color.BLACK);
        font.getData().setScale(0.25f,0.25f);
        for (int k=8;k>=0;k--)
            for (int i=0;i<9;i++)
                for (City c : cities.getCities())
                    if (b1.getBoard(i, k) != 0)
                        if (b1.getBoard(k, i) == c.getPos()) {
                            font.draw(batch, c.getName(), k * 80 + 120, i * 80 + 100);
                        }

        if ((user.getUserX() == 3 && user.getUserY() == 0 || user.getUserX() == 0 && user.getUserY() == 3||user.getUserX() == 2 && user.getUserY() == 8||user.getUserX() == 8 && user.getUserY() == 6) &&  user.getMove() == user.getMoveCount()){
            font.getData().setScale(0.25f,0.25f);
            font.setColor(Color.WHITE);
            font.draw(batch,  c.toString(), 200, 550);
        }

        batch.end();


        //gerideyse
        if (user.getMoveCount() > user.getMove()) {
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

        //Move Functions
        if (user.getMove()> user.getMoveCount()) {
            if (user.getUserX() > 0 && user.getUserX() <= 8 && user.getUserY() == 0) {
                if (userPosCouter < 77.625) {
                    userPosCouter += speed * delta;
                    user.getUserPos().x -= speed * delta;
                }
                if (userPosCouter >= 77.625) {
                    user.setMoveCount(user.getMoveCount() + 1);
                    userPosCouter = 0f;
                    user.setUserX(user.getUserX() - 1);
                }
                user.isDrawable = true;
            } else if (user.getUserY() >= 0 && user.getUserY() < 8 && user.getUserX() == 0) {
                if (userPosCouter < 74) {
                    userPosCouter += speed * delta;
                    user.getUserPos().y += speed * delta;
                }
                if (userPosCouter >= 74) {
                    user.setMoveCount(user.getMoveCount() + 1);
                    userPosCouter = 0f;
                    user.setUserY(user.getUserY() + 1);
                }
                user.isDrawable = true;
            } else if (user.getUserX() >= 0 && user.getUserX() < 8 && user.getUserY() == 8) {
                if (userPosCouter < 77.625) {
                    userPosCouter += speed * delta;
                    user.getUserPos().x += speed * delta;
                }
                if (userPosCouter >= 77.625) {
                    user.setMoveCount(user.getMoveCount() + 1);
                    userPosCouter = 0f;
                    user.setUserX(user.getUserX() + 1);
                }
                user.isDrawable = true;
            } else if (user.getUserY() > 0 && user.getUserY() <= 8 && user.getUserX() == 8 && user.getMove() > user.getMoveCount()) {
                if (userPosCouter < 74.00) {
                    userPosCouter += speed * delta;
                    user.getUserPos().y -= speed * delta;
                }
                if (userPosCouter >= 74) {
                    user.setMoveCount(user.getMoveCount() + 1);
                    userPosCouter = 0f;
                    user.setUserY(user.getUserY() - 1);
                }
                user.isDrawable = true;
            }
        }

        if ((user.getUserX() == 3 && user.getUserY() == 0 || user.getUserX() == 0 && user.getUserY() == 3||user.getUserX() == 2 && user.getUserY() == 8||user.getUserX() == 8 && user.getUserY() == 6) && user.getCardCount() < 1 && user.getMove() == user.getMoveCount()) {

            c = cards.drawCard(user);
            user.isDrawable = false;
            user.setCardCount(1);
        }



        if (user.getUserX() == 8 && user.getUserY() == 8 && user.getMove() < user.getMoveCount())
            user.setMoveCount(1);

        //substracting value for preventing big number
        if (user.getMoveCount() > 32 && user.getMove() > 32) {
            user.setMoveCount(user.getMove() - 32);
            user.setMove(user.getMove() - 32);
        }

        //şehir satın alma
        //TODO şehir satın alma yapılacak.


        if (isHoover) {
            if (fontsize < 1.25f) {
                fontsize += 0.05f;
            }
            button.setScale(fontsize);
        } else {
            if (fontsize > 1f) {
                fontsize -= 0.05f;
            }
            button.setScale(fontsize);
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
