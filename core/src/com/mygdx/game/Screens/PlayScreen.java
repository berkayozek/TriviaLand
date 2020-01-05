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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    TELEPORT,
    NEXTPLAYER;
}

public class PlayScreen implements Screen {
//TODO ÇOK OYUNCULUDA KART ÇEKİLİYOR AMA YAZDIRILMIYOR
    private OrthographicCamera camera;
    private Board b1 = new Board();
    private Cities cities = new Cities();
    private TriviaLand game;
    private Sprite splash,boardSprite;
    private ArrayList<Sprite> userSprite,diceSprite;
    private SpriteBatch batch;
    private Texture boardImage;
    private ArrayList<Texture> userImage,diceImage;
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
    private ArrayList<User> usersArray,gameOverUsers;
    private Card c=(Card) CardDeck.getCardArray().get(0);
    private ExtremeCard ec=(ExtremeCard) ExtremeCardDeck.getExtremeCardArray().get(1);
    private boolean isMoving = false;
    private int whoIsRound = 0;
    private StatustStage stages = StatustStage.DICE;
    private Table buyTable = new Table();
    private Table upgradeTable = new Table();
    private String citiesName = "";
    private Table table2=new Table();
    private ArrayList<Table> cityRentTable = new ArrayList<>();
    private ArrayList<Label> cityRentLabel = new ArrayList<>();
    private Label.LabelStyle labelStyle = new Label.LabelStyle();
    private Viewport viewport;
    public PlayScreen(TriviaLand game,ArrayList<User> users) {
        this.game = game;
        this.usersArray = users;
    }

    @Override
    public void show() {
        System.out.println(usersArray.size());
        camera= new OrthographicCamera(TriviaLand.WIDTH, TriviaLand.HEIGHT);
        camera.setToOrtho(false, TriviaLand.WIDTH, TriviaLand.HEIGHT);
        viewport = new FitViewport(TriviaLand.WIDTH,TriviaLand.HEIGHT,camera);
        batch = new SpriteBatch();
        die = new Die();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        gameOverUsers = new ArrayList<>();
        userImage = new ArrayList<>();
        diceImage = new ArrayList<>();
        userSprite = new ArrayList<>();
        diceSprite = new ArrayList<>();
        for (int i=0;i<usersArray.size();i++) {
            userImage.add(new Texture(usersArray.get(i).getLocation()));
            userImage.get(userImage.size()-1).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            userSprite.add(new Sprite(userImage.get(i)));
            userSprite.get(i).setSize(35,35);
        }
        style = new TextButtonStyle();
        style.font = font;
        stage = new Stage(new ExtendViewport(TriviaLand.WIDTH, TriviaLand.HEIGHT));
        Gdx.input.setInputProcessor(stage);
        buyButton = new TextButton("Buy",style);
        buyButton.getLabel().setColor(Color.BLACK);
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (userCanBuy && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().equals(cities.getTempUser())) {
                    cities.getCities().get(usersArray.get(whoIsRound).getMove()).setUser(usersArray.get(whoIsRound));

                    usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getPrice());
                    userCanBuy = false;
                    usersArray.get(whoIsRound).buy(cities.getCities().get(usersArray.get(whoIsRound).getMove()));
                    citiesName = usersArray.get(whoIsRound).toStringCities();
                    stages = StatustStage.NEXTPLAYER;
                }
            }
        });
        dontBuyButton = new TextButton(" End Turn",style);
        dontBuyButton.getLabel().setColor(Color.BLACK);
        dontBuyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (stages == StatustStage.BUY) {
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
        upgrade1.getLabel().setColor(Color.BLACK);
        upgrade1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(1);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        upgrade2 = new TextButton("2",style);
        upgrade2.getLabel().setColor(Color.BLACK);
        upgrade2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(2);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        upgrade3 = new TextButton("3",style);
        upgrade3.getLabel().setColor(Color.BLACK);
        upgrade3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cities.getCities().get(usersArray.get(whoIsRound).getMove()).built(3);
                upgradeTable.setVisible(false);
                stages = StatustStage.NEXTPLAYER;
            }
        });
        exitUpgrade = new TextButton("End Turn",style);
        exitUpgrade.getLabel().setColor(Color.BLACK);
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
        wantExtremeCard=new TextButton("YES",style);
        wantExtremeCard.getLabel().setColor(Color.BLACK);
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
        dontWantExtremeCard.getLabel().setColor(Color.BLACK);
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
        button.setPosition(1100, 550);
        button.setTransform(true);
        button.getLabel().setColor(Color.BLACK);
        button.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() && stages == StatustStage.DICE) {
                    die.roll();
                    isDie = true;
                    usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() + 16);
                    //usersArray.get(whoIsRound).setMove(usersArray.get(whoIsRound).getMove() + die.getSum());
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
                button.getLabel().setColor(Color.BLACK);
            }
        });

        labelStyle.font = new BitmapFont(Gdx.files.internal("text.fnt")); // TODO Fontları Ayarlamamız lazım.
        labelStyle.fontColor = Color.WHITE;
        cityRentTable.add(new Table());
        cityRentTable.get(0).setPosition(205,40);
        cityRentTable.get(0).setWidth((int)(74*7));
        cityRentTable.get(0).setHeight(28);
        for (int i=2;i<=8;i++){
            if (cities.getCities().get(b1.getBoard(0,i)).getHire()==0 || cities.getCities().get(b1.getBoard(0,i)).getHire()==1000 )
                cityRentLabel.add(new Label("",labelStyle));
            else
                cityRentLabel.add(new Label(cities.getCities().get(b1.getBoard(0,i)).getHire() + " M",labelStyle));
            cityRentLabel.get(i-2).setAlignment(Align.center);
            cityRentTable.get(0).add(cityRentLabel.get(i-2)).width(74);
        }
        //cityRentTable.get(0).debug();




        //Board Resmi
        boardImage = new Texture("board.png");
        boardImage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        boardSprite = new Sprite(boardImage);
        //Zar Resmi
        for (int i=1;i<=6;i++){
            diceImage.add(new Texture("Dice/" + i + ".png"));
            diceImage.get(i-1).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        diceSprite.add(new Sprite(diceImage.get(die.getDie1()-1)));
        diceSprite.add(new Sprite(diceImage.get(die.getDie2()-1)));

        boardSprite.setPosition(100,40);

        diceSprite.get(0).setScale(0.3f);
        diceSprite.get(1).setScale(0.3f);

        diceSprite.get(0).setPosition(240,170);
        diceSprite.get(1).setPosition(380,170);
//küçük bir degisiklik
        stage.addActor(cityRentTable.get(0));
        stage.addActor(button);
        stage.addActor(buyTable);
        stage.addActor(upgradeTable);
        usersArray.get(0).getCities().add(cities.getCities().get(4));
        cities.getCities().get(4).setUser(usersArray.get(0));
        stage.addActor(table2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.WHITE);
        // Üst ve alt
        for (int i = 0; i < 9; i++)
            for (int k = 0; k < 9; k++)
                if (b1.getBoard(k, i) > 1 && b1.getBoard(k, i) < 10 || b1.getBoard(k, i) > 17 && b1.getBoard(k, i) < 25)
                    shape.rect((int) (i * 74 + 129), (int) (k * 77.625 + 40), 70, 99);

        // sol ve sağ
        for (int i = 0; i < 9; i++)
            for (int k = 0; k < 9; k++)
                if (b1.getBoard(k, i) > 25 && b1.getBoard(k, i) <= 32 || b1.getBoard(k, i) > 9 && b1.getBoard(k, i) < 17)
                    shape.rect((int) (i * 77.625 + 100), (int) (k * 74 + 69), 99, 70);

        for (int i = 0; i < 9; i++)
            for (int k = 0; k < 9; k++)
                if (b1.getBoard(k, i) == 1 || b1.getBoard(k, i) == 9 || b1.getBoard(k, i) == 17
                        || b1.getBoard(k, i) == 25)
                    shape.rect((int) (i * 77.625 + 100), (int) (k * 77.625 + 40), 99, 99);

        //Zar butonu
        shape.setColor(Color.RED);

        //Zarın Değişmesi
        diceSprite.get(0).setTexture(diceImage.get(die.getDie1()-1));
        diceSprite.get(1).setTexture(diceImage.get(die.getDie2()-1));
        //Userın Parası Bitirse
        for (User u : usersArray )
            if (u.getMoney()<=0) {
                u.getUserPos().x = 2000;
                u.getUserPos().y = 2000;
                for (City c: cities.getCities())
                    for (City userc: u.getCities())
                        if (c.getUser().equals(userc)) {
                            c.setUser(cities.getTempUser());
                            c.resetCity();
                            u.setMoney(0);
                        }
                gameOverUsers.add(u);
            }
        if (stages == StatustStage.CARD)
            shape.rect(cardx,300,400,350);

        shape.end();
        stage.act(Gdx.graphics.getDeltaTime());
        for (int i=0;i<userSprite.size();i++)
            userSprite.get(i).setCenter(usersArray.get(i).getUserPos().x, usersArray.get(i).getUserPos().y + jumpVariable);

        stage.draw();
        stage.setViewport(viewport);
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        boardSprite.draw(batch);
        diceSprite.get(0).draw(batch);
        diceSprite.get(1).draw(batch);
        for (int i = 0 ; i < userSprite.size() ; i++)
            userSprite.get(i).draw(batch);


        font.setColor(Color.BLACK);
        font.getData().setScale(1f,1f);

        font.getData().setScale(0.25f);


        if (usersArray.size()>=2){
                font.draw(batch, "Player " + usersArray.get(0).getNumber() + "\nMoney = " + usersArray.get(0).getMoney() + "\n" + "City:  " +usersArray.get(0).toStringCities(), 900, 780);

            font.draw(batch,"Player " + usersArray.get(1).getNumber()  + "\nMoney = " + usersArray.get(1).getMoney() + "\n" + "City: "+usersArray.get(1).toStringCities()  ,1100,780);

        } if (usersArray.size()>=3){
            int loc=150;
            font.draw(batch,"Player " + usersArray.get(2).getNumber()  + "\nMoney = " + usersArray.get(2).getMoney() + "\n" + "City: "+usersArray.get(2).toStringCities()  ,900,200);

        } if (usersArray.size()>=4){
            font.draw(batch,"Player " + usersArray.get(3).getNumber()  + "\nMoney = " + usersArray.get(3).getMoney() + "\n" + "City: "+usersArray.get(3).toStringCities()  ,1100,200);


        }


        /////////Go to jail karesi{

        if(usersArray.get(whoIsRound).getUserX()==8 &&usersArray.get(whoIsRound).getUserY()==8 &&usersArray.get(whoIsRound).getJailCount()==0 &&usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()){
            usersArray.get(whoIsRound).getUserPos().x=148;
            usersArray.get(whoIsRound).getUserPos().y=129;
            usersArray.get(whoIsRound).setUserX(0);
            usersArray.get(whoIsRound).setUserY(0);
            usersArray.get(whoIsRound).setJailCount( usersArray.get(whoIsRound).getJailCount()+3);
            usersArray.get(whoIsRound).setInTheJail(true);
        }

        if ((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0
                && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2
                && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8
                && usersArray.get(whoIsRound).getUserY() == 6 || usersArray.get(whoIsRound).getUserY()==8 && usersArray.get(whoIsRound).getUserX()==0 || usersArray.get(whoIsRound).getUserY()==0 && usersArray.get(whoIsRound).getUserX()==0 || usersArray.get(whoIsRound).getUserY()==0 && usersArray.get(whoIsRound).getUserX()==8 )
                &&  usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() && stages == StatustStage.CARD ){
            font.getData().setScale(0.50f,0.50f);
            font.setColor(Color.BLACK);
            font.draw(batch,  c.toString(), 200, 550);

        }
        if (stages == StatustStage.EXTREMECARD && showExtreme){
            timer += delta;
            if (timer<5) {
                font.getData().setScale(0.50f, 0.50f);
                font.setColor(Color.BLACK);
                font.draw(batch, ec.toString(), 200, 550);
            }
            if (timer > 5) {
                stages = StatustStage.NEXTPLAYER;
                showExtreme=false;
                timer = 0;
            }
        }
        if (((usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 2 )||(usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 5)  ) && usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount() &&stages == StatustStage.EXTREMECARD) {
            font.getData().setScale(0.25f);
            font.setColor(Color.BLACK);
            font.draw(batch,"Do you want to draw Xtreme Card?\n(Attention:It is game changer.Be careful to decide.)",850,450);
            wantExtremeCard.setVisible(true);
            dontWantExtremeCard.setVisible(true);
        }
        else{
            wantExtremeCard.setVisible(false);
            dontWantExtremeCard.setVisible(false); //TODO Bunlar Düzenlenecek
        }

        font.getData().setScale(1f);
        if (stages == StatustStage.TELEPORT){
            font.getData().setScale(0.5f);
            font.draw(batch,"Where do you want to go?",850,450);

            Vector3 vector3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (Gdx.input.isTouched() && vector3.x<820 && vector3.y<769){//TODO X ve Y Daha düzgün hesaplanacak
                //shape.rect((int) (k * 74 + 129), (int) (i * 77.625 + 40), 70, 99);
                int x = (int) vector3.x;
                int y = (int) vector3.y;
                if ((x>=100 && x<=199 && y>=40 && y<=139) || (x>=100 && x<=199 && y>=661 && y<=760) || (x>=721 && x<=820 && y>=40 && y<=139) || (x>=721 && x<=820 && y>=661 && y<=760)){
                    x = (int) ((int) (vector3.x-100)/77.625);
                    y = (int) ((int) (vector3.y-40)/77.625);
                    if (y==9)
                        y=8;
                    if (x==9)
                        x=8;
                    if (x==1)
                        x=0;
                    if(y==1)
                        y=0;
                }
                else {
                    x = (((int) vector3.x-129)/74);
                    y = (((int) vector3.y-69)/74);
                }
                usersArray.get(whoIsRound).setUserX(x);
                usersArray.get(whoIsRound).setUserY(y);
                usersArray.get(whoIsRound).getUserPos().x = (int)77.625*x+169;
                usersArray.get(whoIsRound).getUserPos().y = (int)77.625*y+129;
                usersArray.get(whoIsRound).setMove(b1.getBoard(y,x));
                usersArray.get(whoIsRound).setMoveCount(b1.getBoard(y,x));
                stages = StatustStage.NEXTPLAYER;
            }
        }

        batch.end();
        stage.draw();

        if (stages == StatustStage.CARD && cardx<200)
            cardx += delta*100;

        //TODO MOVE FUNCTIONSDA  START CONDITION YOK OYUNCUNUN BİRİ STARTA GELDİĞİ AN OYUN DURUYOR
        //Move Functions
        if (usersArray.get(whoIsRound).getMove()> usersArray.get(whoIsRound).getMoveCount()) {
            c= (Card) CardDeck.getCardArray().get(0);
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
        if (stages==StatustStage.CARD && usersArray.get(whoIsRound).getCardCount()<1) {
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
            usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() + 500);
        }



        //şehir satın alma
        if (usersArray.get(whoIsRound).getMove()<32)
            if (cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().equals(cities.getTempUser())){
                userCanBuy = true;
                buyButton.setVisible(userCanBuy);


                //System.out.println(cities.getCities().get(user.getMove()).getName())
            }

        if (usersArray.get(whoIsRound).getMoveCount()==usersArray.get(whoIsRound).getMove() && isMoving && stages == StatustStage.DICE && !c.equals(CardDeck.getCardArray().get(18)) && !c.equals(CardDeck.getCardArray().get(16)) && cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().equals(cities.getTempUser()) ) {
            stages = StatustStage.BUY;
            buyTable.setVisible(true);
        }
        if (usersArray.get(whoIsRound).getMoveCount()==usersArray.get(whoIsRound).getMove() && isMoving && stages == StatustStage.DICE){
            if (usersArray.get(whoIsRound).getCities().contains(cities.getCities().get(usersArray.get(whoIsRound).getMove())))
                stages = StatustStage.UPGRADE;
            else    if (usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 8)
                stages = StatustStage.TELEPORT;
            else if (!cities.getCities().get(usersArray.get(whoIsRound).getMove()).equals(cities.getTempUser()) && !cities.getCities().get(usersArray.get(whoIsRound).getMove()).getName().equals("Lucky Card")) {
                if (usersArray.get(whoIsRound).isPayRent()) {
                    usersArray.get(whoIsRound).setPayRent(true);
                    stages = StatustStage.RENT;
                } else {
                    usersArray.get(whoIsRound).setPayRent(true);
                    stages = StatustStage.NEXTPLAYER;
                }
            }else if (cities.getCities().get(usersArray.get(whoIsRound).getMove()).equals(cities.getTempUser()))
                stages = StatustStage.BUY;
            else if(((usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 2 )||(usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 5)  ) && usersArray.get(whoIsRound).getExtremeCardCount()<1 &&usersArray.get(whoIsRound).isDrawableExtreme){
                stages = StatustStage.EXTREMECARD;
            }
            else if((usersArray.get(whoIsRound).getUserX() == 3 && usersArray.get(whoIsRound).getUserY() == 0 || usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 3||usersArray.get(whoIsRound).getUserX() == 2 && usersArray.get(whoIsRound).getUserY() == 8||usersArray.get(whoIsRound).getUserX() == 8 && usersArray.get(whoIsRound).getUserY() == 6) && usersArray.get(whoIsRound).getCardCount() < 1 && usersArray.get(whoIsRound).getMove() == usersArray.get(whoIsRound).getMoveCount()){
                stages = StatustStage.CARD;
            }

            else if(!(c.equals(CardDeck.getCardArray().get(18)) || c.equals(CardDeck.getCardArray().get(16)))){
                stages=StatustStage.NEXTPLAYER;
            }
        }
        else if(stages == StatustStage.BUY){
            userCanBuy = true;
            buyTable.setVisible(true);
        }
        else if (stages == StatustStage.NEXTPLAYER){
                whoIsRound++;
            if (whoIsRound > usersArray.size() - 1)
                whoIsRound = 0;
                if (!gameOverUsers.contains(usersArray.get(whoIsRound))) {
                    stages = StatustStage.DICE;
                    isMoving = false;
                }
        }
        else if (stages == StatustStage.CARD && cardx>200){
            //Kart geldiği zaman 5 sn bekliyor sonra devam ediyor.
            timer += delta;
            if (timer>5) {
                timer=0;
                if(c.equals(CardDeck.getCardArray().get(18)) || c.equals(CardDeck.getCardArray().get(16))){
                    stages=StatustStage.DICE;
                }
                else
                    stages = StatustStage.NEXTPLAYER;
            }

        }
        else if (stages == StatustStage.EXTREMECARD)
            table2.setVisible(true);
        if (stages == StatustStage.RENT && usersArray.get(whoIsRound).getMoveCount() == usersArray.get(whoIsRound).getMove() ){
            if(usersArray.get(whoIsRound).isDoubleRent()){
                usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getHire()*2);
                usersArray.get(whoIsRound).setDoubleRent(false);
            }
            else{
                usersArray.get(whoIsRound).setDoubleRent(false);
                usersArray.get(whoIsRound).setMoney(usersArray.get(whoIsRound).getMoney() - cities.getCities().get(usersArray.get(whoIsRound).getMove()).getHire());
            }
            cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().setMoney(cities.getCities().get(usersArray.get(whoIsRound).getMove()).getUser().getMoney() + cities.getCities().get(usersArray.get(whoIsRound).getMove()).getHire());
            stages = StatustStage.NEXTPLAYER;
        }
        if(stages == StatustStage.UPGRADE)
            upgradeTable.setVisible(true);

        if (stages == StatustStage.DICE && usersArray.get(whoIsRound).getUserX() == 0 && usersArray.get(whoIsRound).getUserY() == 0 && usersArray.get(whoIsRound).getJailCount()!=0){
            usersArray.get(whoIsRound).setJailCount(usersArray.get(whoIsRound).getJailCount()-1);
            stages = StatustStage.NEXTPLAYER;
        }
        if (stages != StatustStage.BUY)
            buyTable.setVisible(false);
        if (stages != StatustStage.EXTREMECARD) {
            table2.setVisible(false);
            showExtreme = false;
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
        //if (Gdx.input.isTouched())
            //System.out.println("y: " + (Gdx.graphics.getHeight() - Gdx.input.getY()) + " x: " + Gdx.input.getX());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    public void pause() {

    }


    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
