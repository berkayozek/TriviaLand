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
		cities.add(new City("Diyarbakir", null, 18, 1500, 100));
		// luxery tax 19
		cities.add(new City("Batman", null, 20, 2000, 200));
		cities.add(new City("Erzurum", null, 21, 2800, 200));
		// lucky card 22
		cities.add(new City("Van", null, 23, 1800, 150));
		cities.add(new City("Hakkari", null, 24, 700, 100));
		// jaill 25
		cities.add(new City("Trabzon", null, 26, 2500, 200));
		cities.add(new City("Rize", null, 27, 1800, 250));
		// LUCKY Card 28
		cities.add(new City("Tokat", null, 29, 1800, 150));
		// XTREAM 30
		// Luxery 31
		cities.add(new City("Ankara", null, 32, 3500, 300));
		// GO TO JAILL 1
		cities.add(new City("Eskisehir", null, 2, 2000, 150));
		// LUCKY CARD 3
		cities.add(new City("Kırıkkalee", null, 4, 1800, 150));
		cities.add(new City("Izmir", null, 5, 3200, 300));
		// TAX 6
		cities.add(new City("Denizli", null, 7, 800, 100));
		cities.add(new City("Mugla", null, 8, 1200, 150));
		// TELEPORT 9
		cities.add(new City("Adana", null, 10, 2800, 250));
		// LUCKY CARD 11
		cities.add(new City("Antalya", null, 12, 3200, 300));
		cities.add(new City("Mersin", null, 13, 1500, 100));
		cities.add(new City("Istanbul", null, 14, 4000, 500));
		// XTREAM 15
		cities.add(new City("Edirne", null, 16, 2500, 200));
		// START 17

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
