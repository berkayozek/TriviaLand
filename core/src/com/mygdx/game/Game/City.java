package com.mygdx.game.Game;
//TODO City'nin kiraları ayarlanması lazım
public class City {
	private int pos;
	private String name;
	private User user = null;
	private int price;
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

	public void built(int hireCount) {

		if (hireCount == 1) {
			hire *= 2;
			user.setMoney((int) (user.getMoney() - price*(0.50)));
			// ev
		}
		else if (hireCount == 2) {
			hire *= 3;
			user.setMoney((int) (user.getMoney() - price*(0.75)));
			// ev
		}
		else if (hireCount == 3) {
			hire *= 4;
			user.setMoney((int) (user.getMoney() - price*(1.25)));
			// otel

		}
	}

	public void setUser(User user) {

		this.user = user;

	}


	public void payTax(User user) {

		if(user.getMoney()<hire){
			this.user.setMoney(this.user.getMoney() + user.getMoney());
			user.setMoney(0);
		}
		else{
			user.setMoney(user.getMoney() - hire);
			this.user.setMoney(this.user.getMoney() + hire);
		}


	}

	public void teleport(User user ){

	}
	public Boolean isBoosted() {
		if (this.pos == 8 || this.pos == 16 || this.pos == 24 || this.pos == 32) {
			return true;
		} else {
			return false;
		}
	}



}
