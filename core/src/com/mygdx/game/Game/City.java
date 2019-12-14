package com.mygdx.game.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class City {
	private int pos;

	private String name;
	private User user = null;
	private int price;
	private int hireCount = 1;
	private int hire;

	public City(String name, User user, int pos, int price, int hire) {
		this.name = name;
		this.user = user;
		this.price = price;
		this.hire = hire;
	}

	public int getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getHire() {
		return hire;
	}

	public User getUser() {
		return user;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setHire(int hire) {
		this.hire = hire;
	}

	public void setCityToUser() {
		if (user != null) {
			user.getCities().add(this);
		}
	}

	public void built() {

		if (hireCount == 1) {
			hireCount++;
			hire *= 2;
			// ev
		}
		if (hireCount == 2) {
			hireCount++;
			hire *= 3;
			// ev
		}
		if (hireCount == 3) {
			hireCount++;
			hire *= 3;
			// otel

		}
		if (hireCount == 4) {
			System.out.println("You can't build no more.");
		}
	}

	public void setUser(User user) {

		this.user = user;

	}

	public Boolean isBoosted() {
		if (this.pos == 8 || this.pos == 16 || this.pos == 24 || this.pos == 32) {
			return true;
		} else {
			return false;
		}
	}

}
