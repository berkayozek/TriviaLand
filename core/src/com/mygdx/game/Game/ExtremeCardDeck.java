
package com.mygdx.game.Game;

import java.util.ArrayList;

public class ExtremeCardDeck {

	ArrayList extremeCardArray = new ArrayList<Card>();

	Card ecd1 = new Card("Card1", false, " You can teleport wherever you want! ");
	Card ecd2 = new Card("Card2", true, "You  increase rent of all properties of that you have  fifty percent ");
	Card ecd3 = new Card("Card3", false, "You win fifty percent of your money");
	Card ecd4 = new Card("Card4", false, " You swap your money with the person you want");
	Card ecd5 = new Card("Card5", false, "You win lottery.Get your money from bank.");
	Card ecd6 = new Card("Card6", false, "You lost fifty percent of money.");
	Card ecd7 = new Card("Card7", true, "Decrease rent of all properties that you have fifty percent.");

	public ExtremeCardDeck() {

		extremeCardArray.add(ecd1);
		extremeCardArray.add(ecd2);
		extremeCardArray.add(ecd3);
		extremeCardArray.add(ecd4);
		extremeCardArray.add(ecd5);
		extremeCardArray.add(ecd6);
		extremeCardArray.add(ecd7);

	}

	public void drawExtremeCard(User user) {
		int selectedCard = (int) (Math.random() * 20 + 1);
		if (extremeCardArray.get(selectedCard - 1).equals(ecd1)) {
			ecd1(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd2)) {
			ecd2(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd3)) {
			ecd3(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd4)) {
			ecd4(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd5)) {
			ecd5(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd6)) {
			ecd6(user);
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd7)) {
			ecd7(user);
		}

	}

	public void ecd1(User user) {
       // TODO: 14.12.2019 teleport kısmı
		// butonlardan sonra ışınlama


	}

	public void ecd2(User user) {

		for (int i = 0; i < user.getCities().size(); i++) {
			user.getCities().get(i).setHire(user.getCities().get(i).getHire() * 2);

		}
	}

	public void ecd3(User user) {

		for (int i = 0; i < user.getCities().size(); i++) {
			user.getCities().get(i).setHire(user.getCities().get(i).getHire() * 2);
		}

	}

	public void ecd4(User user) {

		user.getMoney();

	}

	public void ecd5(User user) {
		user.setMoney(user.getMoney() + 5000);
	}

	public void ecd6(User User) {

		ecd6.getUser().setMoney(ecd6.getUser().getMoney() / 2);

	}

	public void ecd7(User User) {

		for (int i = 0; i < User.getCities().size(); i++) {
			User.getCities().get(i).setHire(User.getCities().get(i).getHire() / 2);
		}

	}

}
