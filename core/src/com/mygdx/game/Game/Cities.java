package com.mygdx.game.Game;
import java.util.ArrayList;

public class Cities {
	ArrayList<City> cities = new ArrayList<>();

	public User getTempUser() {
		return tempUser;
	}

	public void setTempUser(User tempUser) {
		this.tempUser = tempUser;
	}

	User tempUser;

	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public Cities() {
		cities.add(new City("Start",new User("admin12",false),0,0,0));
		cities.add(new City("Diyarbakir", tempUser, 1, 1500, 100));
		cities.add(new City("Tax",new User("admin1",false),2,0,1000)); // user uzerınden çağır
		cities.add(new City("Batman", tempUser, 3, 2000, 200));
		cities.add(new City("Erzurum", tempUser, 4, 2800, 200));
		cities.add(new City("Lucky Card",new User("admin5",false),5,0,0));//lucky card
		cities.add(new City("Van", tempUser, 6, 1800, 150));
		cities.add(new City("Hakkari", tempUser, 7, 700, 100));
		cities.add(new City("Jail",new User("admin4",false),8,0,0)); // jail
		cities.add(new City("Trabzon", tempUser, 9, 2500, 200));
		cities.add(new City("Rize", tempUser, 10, 1800, 250));
		cities.add(new City("Lucky Card",new User("admin6",false),11,0,0));//lucky card
		cities.add(new City("Tokat", tempUser, 12, 1800, 150));
		cities.add(new City("Extreme Card",new User("admin7",false),13,0,0));//extream
		cities.add(new City("Tax",new User("admin2",false),14,0,1000));//tax
		cities.add(new City("Ankara", tempUser, 15, 3500, 300));
		cities.add(new City("Teleport",new User("admin5",false),16,0,0));//teleport
		cities.add(new City("Eskisehir", tempUser, 17, 2000, 150));
		cities.add(new City("Lucky Card",new User("admin8",false),18,0,0));//lucky card
		cities.add(new City("Kırıkkale", tempUser, 19, 1800, 150));
		cities.add(new City("Izmir", tempUser, 20, 3200, 300));
		cities.add(new City("Tax",new User("admin3",false),21,0,1000));//tax
		cities.add(new City("Denizli", tempUser, 22, 800, 100));
		cities.add(new City("Mugla", tempUser, 23, 1200, 150));
		cities.add(new City("Go to Jail",new User("admin9",false),24,0,0));//Go to jail
		cities.add(new City("Adana", tempUser, 25, 2800, 250));
		cities.add(new City("Lucky Card",new User("admin10",false),5,0,0));//lucky
		cities.add(new City("Antalya", tempUser, 27, 3200, 300));
		cities.add(new City("Mersin", tempUser, 28, 1500, 100));
		cities.add(new City("Istanbul", tempUser, 29, 4000, 500));
		cities.add(new City("Extreme Card",new User("admin11",false),30,0,0));//extreme card
		cities.add(new City("Edirne", tempUser, 31, 2500, 200));
		// start noktası: cities.add(null);

	}


	public void Boost(City city) {
		if (city.isBoosted()) {
			/////////////////// OYUN BELİRLEYECEK//////////////////
			if (city.getPos() == 0) {

			} else if (city.getPos() == 0) {

			} else if (city.getPos() == 0) {

			} else if (city.getPos() == 0) {

			}
		}

	}

}
