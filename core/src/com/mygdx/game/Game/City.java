package com.mygdx.game.Game;
//TODO City'nin kiraları ayarlanması lazım
public class City {
	private int pos;
	private String name;
	private User user = null;
	private int price;


	private int hire;
	private int firsthire=0;
	private int hireCountt=0;



	private int hireCount;

	public City(String name, User user, int pos, int price, int hire) {
		this.name = name;
		this.user = user;
		this.price = price;
		this.hire = hire;
		hireCount=0;
		firsthire=hire;

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

	public int getHireCountt() {
		return hireCountt;
	}

	public void setHireCountt(int hireCountt) {
		this.hireCountt = hireCountt;
	}
	public void built(int hireCount) {
		this.hireCount = hireCount;
		if (hireCount < 4) {
			if (hireCount == 1) {
				this.hireCount = 1;
				hire *= 2;
				user.setMoney((int) (user.getMoney() - price * hire*2));
				// ev

			} else if (hireCount == 2) {
				this.hireCount = 2;
				hire *= 4;
				user.setMoney((int) (user.getMoney() - hire*4));
				// ev

			} else if (hireCount == 3) {
				this.hireCount = 3;
				hire *= 8;
				user.setMoney((int) (user.getMoney() - hire*8));
				// otel

			}
			else if(hireCount==0){
				hire=firsthire;
			}

		}
	}
	public void resetCity(){
		hire = firsthire;
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

	public Boolean isBoosted() {
		if (this.pos == 8 || this.pos == 16 || this.pos == 24 || this.pos == 32) {
			return true;
		} else {
			return false;
		}
	}

	public int getHireCount() {
		return hireCount;
	}

	public void setHireCount(int hireCountt) {
		this.hireCount = hireCountt;
	}

}
