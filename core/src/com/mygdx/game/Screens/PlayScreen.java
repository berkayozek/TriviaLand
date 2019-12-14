package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.*;
import com.mygdx.game.TriviaLand;

import java.util.ArrayList;

public class PlayScreen implements Screen {
//TODO ÇOK OYUNCULUDA KART ÇEKİLİYOR AMA YAZDIRILMIYOR
    private Board b1 = new Board();
    private Cities cities = new Cities();
    private TriviaLand game;
    private Sprite splash;
    private ArrayList<Sprite> citiesSprite;
    private ArrayList<Sprite> userSprite;
    private SpriteBatch batch;
    private ArrayList<Texture> citiesImage;
    private ArrayList<Texture> userImage;
    private Die die;
    private ShapeRenderer shape = new ShapeRenderer();
    private float jumpVariable = 0;
    private boolean isJump = true;
    private BitmapFont font;
    private boolean isDie = false;
    private TextButton button;
    private TextButton buyButton;
    private TextButtonStyle style;
    private Stage stage;
    private float fontsize = 1f;
    private boolean isHoover = false;
    private CardDeck cards=new CardDeck();
    private float userPosCouter = 0;
    private float speed = 100f;
    private boolean userCanBuy = false;
    private ArrayList<User> usersArray;
    private Card c;
    private boolean isMoving = false;
    private int whoIsRound = 0;
    private String citiesName = "";

    public PlayScreen(TriviaLand game,ArrayList<User> users) {
        this.game = game;
        this.usersArray = users;
    }



    @Override
    public void show() {
        System.out.println(usersArray.size());
        batch = new SpriteBatch();
        die = new Die();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        userImage = new ArrayList<>();
        userSprite = new ArrayList<>();
        for (int i=0;i<usersArray.size();i++) {
            userImage.add(new Texture("Hat.png"));
            userImage.get(userImage.size()-1).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            userSprite.add(new Sprite(userImage.get(i)));
            userSprite.get(i).setSize(35,35);
        }
        style = new TextButtonStyle();
        style.font = font;
        stage = new Stage(new ExtendViewport(800, 920));
        Gdx.input.setInputProcessor(stage);
        buyButton = new TextButton("Satın Al",style);
        buyButton.setPosition(300,260);
        buyButton.setVisible(userCanBuy);
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (userCanBuy && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser()==null) {
                    cities.getCities().get(usersArray.get(whoIsRound).getMove()).setUser(usersArray.get(whoIsRound));
                    usersArray.get(whoIsRound).getCities().add(cities.getCities().get(usersArray.get(whoIsRound).getMove()));
                    citiesName +=usersArray.get(whoIsRound).getCities().get(usersArray.get(whoIsRound).getCities().size()-1).getName();
                    usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getPrice());
                    userCanBuy = false;
                }
            }
        });
        button = new TextButton("Zar At", style);
        button.setPosition(420, 460);
        button.setTransform(true);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()) {
                    die.roll();
                    isDie = true;
                    usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() + die.getDie1());
                    isMoving = true;
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

        //TODO şehirlerin png leri yapılacak
        citiesImage.add(new Texture("Trabzon.png"));
        citiesImage.get(0).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        for (int i = 0; i < 1; i++) {
            citiesSprite.add(new Sprite(citiesImage.get(0)));
            citiesSprite.get(i).setSize(99, 70);
        }
        stage.addActor(button);
        stage.addActor(buyButton);
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
        stage.act(Gdx.graphics.getDeltaTime());
        for (int i=0;i<userSprite.size();i++)
            userSprite.get(i).setCenter(usersArray.get(i).getUserPos().x, usersArray.get(i).getUserPos().y + jumpVariable);

        stage.draw();

        for (int i = 0; i < citiesSprite.size(); i++)
            citiesSprite.get(i).setCenter((int) ((0) * 74 + 150), (int) (i+2 * 74 + 105));


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

        for (int i = 0 ; i < userSprite.size() ; i++)
            userSprite.get(i).draw(batch);


        font.setColor(Color.WHITE);
        font.getData().setScale(1f,1f);

        if (isDie)
            font.draw(batch, String.valueOf(die.getDie1()), 300, 250);

        font.getData().setScale(0.25f);
        if (usersArray.get(0).getCities().size()!=0)
            font.draw(batch,"Money = " + usersArray.get(whoIsRound).getMoney() + "\n" + "City: " + citiesName,940,200);

        font.setColor(Color.BLACK);

        if ((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0
                && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2
                && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8
                && usersArray.get(whoIsRound).getUserY() == 6)
                &&  usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()){
            font.getData().setScale(0.50f,0.50f);
            font.setColor(Color.WHITE);
            font.draw(batch,  c.toString(), 200, 550);
        }

        batch.end();


        //Move Functions
        if (usersArray.get(whoIsRound).getMove()> usersArray.get(whoIsRound).getMoveCount()) {
            if (usersArray.get(whoIsRound).getUserX() > 0 && usersArray.get(whoIsRound).getUserX() <= 8 && usersArray.get(whoIsRound).getUserY() == 0) {
                if (userPosCouter < 77.625) {
                    userPosCouter += speed * delta;
                    usersArray.get(whoIsRound).getUserPos().x -= speed * delta;
                }
                if (userPosCouter >= 77.625) {
                    usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() + 1);
                    userPosCouter = 0f;
                    usersArray.get(whoIsRound).setUserX(usersArray.get(whoIsRound).getUserX() - 1);
                }
                usersArray.get(whoIsRound).isDrawable = true;
            } else if (usersArray.get(whoIsRound).getUserY() >= 0 && usersArray.get(whoIsRound).getUserY() < 8 && usersArray.get(whoIsRound).getUserX() == 0) {
                if (userPosCouter < 74) {
                    userPosCouter += speed * delta;
                    usersArray.get(whoIsRound).getUserPos().y += speed * delta;
                }
                if (userPosCouter >= 74) {
                    usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() + 1);
                    userPosCouter = 0f;
                    usersArray.get(whoIsRound).setUserY(usersArray.get(whoIsRound).getUserY() + 1);
                }
                usersArray.get(whoIsRound).isDrawable = true;
            } else if (usersArray.get(whoIsRound).getUserX() >= 0 && usersArray.get(whoIsRound).getUserX() < 8 && usersArray.get(whoIsRound).getUserY() == 8) {
                if (userPosCouter < 77.625) {
                    userPosCouter += speed * delta;
                    usersArray.get(whoIsRound).getUserPos().x += speed * delta;
                }
                if (userPosCouter >= 77.625) {
                    usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() + 1);
                    userPosCouter = 0f;
                    usersArray.get(whoIsRound).setUserX(usersArray.get(whoIsRound).getUserX() + 1);
                }
                usersArray.get(whoIsRound).isDrawable = true;
            } else if (usersArray.get(whoIsRound).getUserY() > 0 && usersArray.get(whoIsRound).getUserY() <= 8 && usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getMove() > usersArray.get(whoIsRound).getMoveCount()) {
                if (userPosCouter < 74.00) {
                    userPosCouter += speed * delta;
                    usersArray.get(whoIsRound).getUserPos().y -= speed * delta;
                }
                if (userPosCouter >= 74) {
                    usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() + 1);
                    userPosCouter = 0f;
                    usersArray.get(whoIsRound).setUserY(usersArray.get(whoIsRound).getUserY() - 1);
                }
                usersArray.get(whoIsRound).isDrawable = true;
            }
        }

        //CARD Çekme
        if ((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2 && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 6) && usersArray.get(whoIsRound).getCardCount() < 1 && usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()) {

            c = cards.drawCard(usersArray.get(whoIsRound),usersArray);
            usersArray.get(whoIsRound).isDrawable = false;
            usersArray.get(whoIsRound).setCardCount(1);
        }



        if (usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 0 && usersArray.get(whoIsRound).getMove() < usersArray.get(whoIsRound).getMoveCount())
            usersArray.get(whoIsRound).setMoveCount(1);

        //substracting value for preventing big number
        if (usersArray.get(whoIsRound).getMoveCount() > 32 && usersArray.get(whoIsRound).getMove() > 32) {
            usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() - 32);
            usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() - 32);
        }



        //şehir satın alma
        //TODO şehir satın alma yapılacak.
        if (cities.getCities().get(usersArray.get(whoIsRound).getMove())!=null && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser() == null){
            userCanBuy = true;
            buyButton.setVisible(userCanBuy);
            //System.out.println(cities.getCities().get(user.getMove()).getName())
        }

        if (usersArray.get(whoIsRound).getMoveCount()==usersArray.get(whoIsRound).getMove() && isMoving && !userCanBuy){
            if (whoIsRound==usersArray.size()-1)
                whoIsRound = 0;
            else
                whoIsRound++;

            isMoving = false;
        }

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
        Vector2 size = Scaling.fit.apply(1300, 800, width, height);
        int viewportX = (int)(width - size.x) / 2;
        int viewportY = (int)(height - size.y) / 2;
        int viewportWidth = (int)size.x;
        int viewportHeight = (int)size.y;
        Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
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
