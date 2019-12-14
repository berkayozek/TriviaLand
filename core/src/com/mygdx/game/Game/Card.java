package com.mygdx.game.Game;

public class Card {
	//TODO Extreme card ve Card'ı tek bir super card classın altında toplayalım (LuckyCard extends Cards ve ExtremeCard extends Cards olsun)
	private String name;
	private User User;
	private boolean isUsable;
	private int cardNumber;
	private String cardDescription;
	static int cardCounter = 1;

	public Card(String name, boolean isUsable, String cD) {

		this.name = name;
		this.isUsable = isUsable;
		this.cardNumber = cardCounter;
		cardDescription = cD;
		cardCounter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser(User User) {
		this.User = User;
	}

	public User getUser() {
		return this.User;
	}

	public String toString() {
		return cardDescription;
	}

}
