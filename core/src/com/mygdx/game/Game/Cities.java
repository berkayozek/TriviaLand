package com.mygdx.game.Game;
import java.util.ArrayList;

public class Cities {
	ArrayList<City> cities = new ArrayList<>();


	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public Cities() {
		cities.add(null);
		cities.add(new City("Diyarbakir", null, 1, 1500, 100));
		cities.add(new City("Tax",new User("admin1",false),2,0,1000)); // user uzerınden çağır
		cities.add(new City("Batman", null, 3, 2000, 200));
		cities.add(new City("Erzurum", null, 4, 2800, 200));
		cities.add(null);//lucky card
		cities.add(new City("Van", null, 6, 1800, 150));
		cities.add(new City("Hakkari", null, 7, 700, 100));
		cities.add(null); // go to jail
		cities.add(new City("Trabzon", null, 9, 2500, 200));
		cities.add(new City("Rize", null, 10, 1800, 250));
		cities.add(null);//lucky card
		cities.add(new City("Tokat", null, 12, 1800, 150));
		cities.add(null);//extream
		cities.add(new City("Tax",new User("admin2",false),14,0,1000));//tax
		cities.add(new City("Ankara", null, 15, 3500, 300));
		cities.add(null);//go to jail
		cities.add(new City("Eskisehir", null, 17, 2000, 150));
		cities.add(null);//lucky card
		cities.add(new City("Kırıkkale", null, 19, 1800, 150));
		cities.add(new City("Izmir", null, 20, 3200, 300));
		cities.add(new City("Tax",new User("admin3",false),21,0,1000));//tax
		cities.add(new City("Denizli", null, 22, 800, 100));
		cities.add(new City("Mugla", null, 23, 1200, 150));
		cities.add(null);//teleport
		cities.add(new City("Adana", null, 25, 2800, 250));
		cities.add(null);//lucky
		cities.add(new City("Antalya", null, 27, 3200, 300));
		cities.add(new City("Mersin", null, 28, 1500, 100));
		cities.add(new City("Istanbul", null, 29, 4000, 500));
		cities.add(null);//extreme card
		cities.add(new City("Edirne", null, 31, 2500, 200));
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
