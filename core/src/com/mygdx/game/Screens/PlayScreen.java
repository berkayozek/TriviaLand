package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Game.*;
import com.mygdx.game.TriviaLand;


import java.util.ArrayList;

enum StatustStage{
    DICE,
    BUY,
    RENT,
    UPGRADE,
    CARD,
    EXTREMECARD,
    NEXTPLAYER;
}

public class PlayScreen implements Screen {
//TODO ÇOK OYUNCULUDA KART ÇEKİLİYOR AMA YAZDIRILMIYOR
    private OrthographicCamera camera;
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
    private TextButton buyButton,dontBuyButton,upgrade1,upgrade2,upgrade3,exitUpgrade;
    private TextButton wantExtremeCard,dontWantExtremeCard;
    private TextButtonStyle style;
    private Stage stage;
    private float fontsize = 1f;
    private boolean isHoover = false,showExtreme = false;
    private CardDeck cards=new CardDeck();
    private ExtremeCardDeck extremecards=new ExtremeCardDeck();
    private float userPosCouter = 0,timer = 0f,cardx;
    private float speed = 100f;
    private boolean userCanBuy = false;
    private ArrayList<User> usersArray;
    private Card c=(Card) CardDeck.getCardArray().get(0);
    private ExtremeCard ec=(ExtremeCard) ExtremeCardDeck.getExtremeCardArray().get(1);
    private boolean isMoving = false;
    private int whoIsRound = 0;
    private StatustStage stages = StatustStage.DICE;
    private Table buyTable = new Table();
    private Table upgradeTable = new Table();
    private String citiesName = "";
    private Table table2=new Table();
    public PlayScreen(TriviaLand game,ArrayList<User> users) {
        this.game = game;
        this.usersArray = users;
    }

    @Override
    public void show() {
        System.out.println(usersArray.size());
        camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        buyButton = new TextButton("Buy",style);
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (userCanBuy && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser()==null) {
                    cities.getCities().get(usersArray.get(whoIsRound).getMove()).setUser(usersArray.get(whoIsRound));
                    usersArray.get(whoIsRound).getCities().add(cities.getCities().get(usersArray.get(whoIsRound).getMove()));
                    citiesName +=usersArray.get(whoIsRound).getCities().get(usersArray.get(whoIsRound).getCities().size()-1).getName();
                    usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getPrice());
                    userCanBuy = false;
                    stages = StatustStage.NEXTPLAYER;
                }
            }
        });
        dontBuyButton = new TextButton(" End Turn",style);
        dontBuyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (stages == StatustStage.BUY) {
                    System.out.println("Tıkladın");
                    userCanBuy = false;
                    stages = StatustStage.NEXTPLAYER;
                }else if (stages == StatustStage.CARD){
                    stages = StatustStage.NEXTPLAYER;
                }else if (stages == StatustStage.EXTREMECARD){
                    stages = StatustStage.NEXTPLAYER;
                }
            }
        });
        buyTable.setPosition(1100,400);
        buyTable.setVisible(false);
        buyTable.add(buyButton);
        buyTable.row();
        buyTable.add(dontBuyButton).padBottom(10);
        upgrade1 = new TextButton("1",style);
        upgrade1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(1);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        upgrade2 = new TextButton("2",style);
        upgrade2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(2);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        upgrade3 = new TextButton("3",style);
        upgrade3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(3);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        exitUpgrade = new TextButton("End Turn",style);
        exitUpgrade.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        upgradeTable.setPosition(1100,400);
        upgradeTable.setSize(300,300);
        upgradeTable.setVisible(false);
        upgradeTable.add(upgrade1).width(100);
        upgradeTable.add(upgrade2).width(100);
        upgradeTable.add(upgrade3).width(100);
        upgradeTable.row();
        upgradeTable.add(exitUpgrade).colspan(3);
        button = new TextButton("Zar At", style);
        wantExtremeCard=new TextButton("YES",style);
            wantExtremeCard.addListener(new ClickListener(){
                public void clicked(InputEvent event,float x, float y){
                    usersArray.get(whoIsRound).setActiveExtremeCard(true);
                    if(stages==StatustStage.EXTREMECARD &&usersArray.get(whoIsRound).getActiveExtremeCard()==true){
                            ec = extremecards.drawExtremeCard(usersArray.get(whoIsRound), usersArray);
                            usersArray.get(whoIsRound).isDrawableExtreme = false;
                            usersArray.get(whoIsRound).setExtremeCardCount(1);
                            showExtreme = true;
                    }
                }
            });
        dontWantExtremeCard= new TextButton("NO",style);
        dontWantExtremeCard.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x, float y){
                usersArray.get(whoIsRound).isDrawableExtreme = false;
                usersArray.get(whoIsRound).setExtremeCardCount(1);
                usersArray.get(whoIsRound).setActiveExtremeCard(false);
                stages = StatustStage.NEXTPLAYER;
                showExtreme = false;
            }
        });

        table2.setPosition(1100,400);
        table2.add(wantExtremeCard);
        table2.add(dontWantExtremeCard).padRight(100);
        table2.setVisible(false);
        button = new TextButton("ROLL", style);
        button.setPosition(420, 460);
        button.setTransform(true);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() && stages == StatustStage.DICE) {
                    die.roll();
                    isDie = true;
                    usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() + 30);

                    //usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() + die.getDie1());
                    isMoving = true;

                    if(c!=null &&c.equals(CardDeck.getCardArray().get(18))  || c!=null && c.equals(CardDeck.getCardArray().get(16)) ){
                        c=(Card) CardDeck.getCardArray().get(0);
                    }
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
        stage.addActor(buyTable);
        stage.addActor(upgradeTable);
        usersArray.get(0).getCities().add(cities.getCities().get(4));
        cities.getCities().get(4).setUser(usersArray.get(0));
        stage.addActor(table2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setProjectionMatrix(camera.combined);
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
        shape.setColor(Color.WHITE);

        if (stages == StatustStage.CARD)
            shape.rect(cardx,300,400,350);

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

        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (int i = 0; i < citiesSprite.size(); i++)
            citiesSprite.get(i).draw(batch);

        for (int i = 0 ; i < userSprite.size() ; i++)
            userSprite.get(i).draw(batch);


        font.setColor(Color.WHITE);
        font.getData().setScale(1f,1f);

        if (isDie)
            font.draw(batch, String.valueOf(die.getDie1()), 300, 250);

        font.getData().setScale(0.25f);

        if (usersArray.size()>=2){
            font.draw(batch,"Player 1 \n Money = " + usersArray.get(0).getMoney() + "\n" + "City:  " ,900,780);
            font.draw(batch,"Player 2 \n Money = " + usersArray.get(1).getMoney() + "\n" + "City: " ,1100,780);
        } if (usersArray.size()>=3){
            font.draw(batch,"Player 3 \n Money = " + usersArray.get(2).getMoney() + "\n" + "City: " ,900,200);
        } if (usersArray.size()>=4){
            font.draw(batch,"Player 4 \n Money = " + usersArray.get(3).getMoney() + "\n" + "City: " ,1100,200);
        }


        if ((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0
                && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2
                && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8
                && usersArray.get(whoIsRound).getUserY() == 6 || usersArray.get(whoIsRound).getUserY()==8 && usersArray.get(whoIsRound).getUserX()==0 || usersArray.get(whoIsRound).getUserY()==0 && usersArray.get(whoIsRound).getUserX()==0 || usersArray.get(whoIsRound).getUserY()==0 && usersArray.get(whoIsRound).getUserX()==8 )
                &&  usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() && stages == StatustStage.CARD ){
            font.getData().setScale(0.50f,0.50f);
            font.setColor(Color.WHITE);
            font.draw(batch,  c.toString(), 200, 550);

        }
        if (stages == StatustStage.EXTREMECARD && showExtreme){
            timer += delta;
            if (timer<5) {
                font.getData().setScale(0.50f, 0.50f);
                font.setColor(Color.WHITE);
                font.draw(batch, ec.toString(), 200, 550);
            }
            if (timer > 5) {
                stages = StatustStage.NEXTPLAYER;
                timer = 0;
            }
        }
        if ((usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 2 || ec==ExtremeCardDeck.getExtremeCardArray().get(0) ) && usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() &&stages == StatustStage.EXTREMECARD&& usersArray.get(whoIsRound).getExtremeCardCount()<1 &&usersArray.get(whoIsRound).isDrawableExtreme) {
            font.getData().setScale(0.25f,0.25f);
            font.setColor(Color.WHITE);
            font.draw(batch,"Do you want to draw Xtreme Card?\n(Attention:It is game changer.Be careful to decide.)",850,450);
            wantExtremeCard.setVisible(true);
            dontWantExtremeCard.setVisible(true);
        }
        else{
            wantExtremeCard.setVisible(false);
            dontWantExtremeCard.setVisible(false);
        }

        font.getData().setScale(1f);

        batch.end();

        if (stages == StatustStage.CARD && cardx<200)
            cardx += delta*100;


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
                usersArray.get(whoIsRound).setCardCount(0);

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

                usersArray.get(whoIsRound).setCardCount(0);

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
                usersArray.get(whoIsRound).setCardCount(0);

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
                usersArray.get(whoIsRound).setCardCount(0);

            }
        }

        //CARD Çekme
        if ((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2 && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 6) && usersArray.get(whoIsRound).getCardCount() < 1 && usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()) {
            c = cards.drawCard(usersArray.get(whoIsRound),usersArray);
            usersArray.get(whoIsRound).isDrawable = false;
            usersArray.get(whoIsRound).setCardCount(1);
            stages = StatustStage.CARD;
        }

        /////////EXTREMECARD
        if((usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() ==2 ) && usersArray.get(whoIsRound).getExtremeCardCount()<1 &&usersArray.get(whoIsRound).isDrawableExtreme){
            stages=StatustStage.EXTREMECARD;

        }


        if (usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 0 && usersArray.get(whoIsRound).getMove() < usersArray.get(whoIsRound).getMoveCount())
            usersArray.get(whoIsRound).setMoveCount(1);

        //substracting value for preventing big number
        if (usersArray.get(whoIsRound).getMoveCount() > 32 && usersArray.get(whoIsRound).getMove() > 32) {
            usersArray.get(whoIsRound).setMoveCount(usersArray.get(whoIsRound).getMoveCount() - 32);
            usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() - 32);
        }



        //şehir satın alma
        if (usersArray.get(whoIsRound).getMove()<32)
            if (cities.getCities().get(usersArray.get(whoIsRound).getMove())!=null && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser() == null){
                userCanBuy = true;
                buyButton.setVisible(userCanBuy);
                usersArray.get(whoIsRound).buy(cities.getCities().get(usersArray.get(whoIsRound).getMove()));

                //System.out.println(cities.getCities().get(user.getMove()).getName())
            }

        if (usersArray.get(whoIsRound).getMoveCount()==usersArray.get(whoIsRound).getMove() && isMoving && stages == StatustStage.DICE && !c.equals(CardDeck.getCardArray().get(18)) && !c.equals(CardDeck.getCardArray().get(16)) && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser() == null) {
            stages = StatustStage.BUY;
            buyTable.setVisible(true);
        }
        if (usersArray.get(whoIsRound).getMoveCount()==usersArray.get(whoIsRound).getMove() && isMoving && stages == StatustStage.DICE){
            if (usersArray.get(whoIsRound).getCities().contains(cities.getCities().get(usersArray.get(whoIsRound).getMove())))
                stages = StatustStage.UPGRADE;
            else if (cities.getCities().get(usersArray.get(whoIsRound).getMove()) != null && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser() != null)
                stages = StatustStage.RENT;
            else if (cities.getCities().get(usersArray.get(whoIsRound).getMove()) != null)
                stages = StatustStage.BUY;
            else
                stages = StatustStage.NEXTPLAYER;
        }
        else if(stages == StatustStage.BUY){
            userCanBuy = true;
            buyTable.setVisible(true);
        }
        else if (stages == StatustStage.NEXTPLAYER){
            if (whoIsRound==usersArray.size()-1)
                whoIsRound = 0;
            else
                whoIsRound++;
            isMoving = false;
            stages = StatustStage.DICE;
        }
        else if (stages == StatustStage.CARD && cardx>200){
            //Kart geldiği zaman 5 sn bekliyor sonra devam ediyor.
            timer += delta;
            if (timer>5) {
                stages = StatustStage.NEXTPLAYER;
                timer=0;

                if(c.equals(CardDeck.getCardArray().get(18))){
                    stages=StatustStage.DICE; }
                if(c.equals(CardDeck.getCardArray().get(16))){
                    stages=StatustStage.DICE;
                }
            }

        }
        else if (stages == StatustStage.EXTREMECARD)
            table2.setVisible(true);

        if (stages == StatustStage.RENT && usersArray.get(whoIsRound).getMoveCount() == usersArray.get(whoIsRound).getMove() ){
            usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getHire());
            cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().setMoney(cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().getMoney() + cities.getCities().get(usersArray.get(whoIsRound).getMove()).getHire());
            stages = StatustStage.NEXTPLAYER;
        }
        if(stages == StatustStage.UPGRADE)
            upgradeTable.setVisible(true);


        if (stages == StatustStage.DICE && usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 0 && usersArray.get(whoIsRound).getJailCount()!=0){
            usersArray.get(whoIsRound).setJailCount(usersArray.get(whoIsRound).getJailCount()-1);
            stages = StatustStage.NEXTPLAYER;
        }
        else if (stages != StatustStage.BUY)
            buyTable.setVisible(false);
        else if (stages != StatustStage.EXTREMECARD) {
            table2.setVisible(false);
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

        if (Gdx.input.isTouched())
            System.out.println("y: " + (Gdx.graphics.getHeight() - Gdx.input.getY()) + " x: " + Gdx.input.getX());
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
