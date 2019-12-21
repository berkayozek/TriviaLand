package com.mygdx.game.Game;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class User {
	private String name;
	private int money;
	private ArrayList<City> Ucities = new ArrayList<>();
	private ArrayList<Card> cards = new ArrayList<>();
	private int jailCount;
	private int move = 0;
	private int moveCount=0;
	private int userX = 8;
	private int userY = 0;

	private int pos;
	private Vector2 userPos = new Vector2();
	private Boolean isBot;
	private static int userCounter = 0;
	private int userNo;
	public boolean isDrawable = true;
	public boolean isDrawableExtreme=true;
	private int cardCount = 0;



	private int ExtremeCardCount = 0;
	public User(String name, Boolean isBot) {
		userCounter++;
		userNo = userCounter;
		this.name = name;
		this.isBot = isBot;
		money = 15000;
		jailCount = 0;
		pos = 1;
		userPos.x = 769;
		userPos.y = 129;
	}

	public Vector2 getUserPos() {
		return userPos;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int newPos) {
		pos = newPos;

	}

	public void move(int number) {
		if (number + pos <= 32)
			pos += number;
		else
			pos = number - (32 - pos);

		System.out.println("User's new position: " + pos);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<City> getCities() {
		return Ucities;
	}

	public void setCities(ArrayList<City> cities) {
		this.Ucities = cities;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getJailCount() {
		return jailCount;
	}

	public void setJailCount(int jailCount) {
		this.jailCount = jailCount;
	}

	public Boolean getIsBot() {
		return isBot;
	}

	public void setIsBot(Boolean isBot) {
		this.isBot = isBot;
	}


	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;

	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;

	}

	public void setCardCount(int i){
		cardCount=i;
	}

	public int getCardCount(){
		return cardCount;
	}

	public void setUserX(int userX) {
		this.userX = userX;
	}

	public void setUserY(int userY) {
		this.userY = userY;
	}

	public int getUserX() {
		return userX;
	}

	public int getUserY() {
		return userY;
	}

	public void buy(City city){
		Ucities.add(city);
	}

	public int getExtremeCardCount() {
		return ExtremeCardCount;
	}
	public void setExtremeCardCount(int extremeCardCount) {
		ExtremeCardCount = extremeCardCount;
	}
}
