package com.mygdx.game.Game;

import java.util.ArrayList;

public class CardDeck {



	//TODO Jail veya Işınlandığı zaman Moveları arttırmamız lazım.
///////////////ELİNDE TUTULAMAYAN KARTLARTI SİLMEYİ DENE//////////////////
	private static ArrayList cardArray = new ArrayList<Card>();
	Card card1 = new Card("Card1", false, "You have to pay bills.(200TL)");  ////YAPILDI
	Card card2 = new Card("Card2", true, "You will pay double rent\n in the next city."); /////YAPILDI
	Card card3 = new Card("Card3", false, "Go to Jail."); /////YAPILDI
	Card card4 = new Card("Card4", false, "Go to Teleport");//YAPILDI
	Card card5 = new Card("Card5", false, "You win lottery.\nGet your money from bank."); ////YAPILDI
	Card card6 = new Card("Card6", false, "You have to pay bills.\n(200TL)");   ////YAPILDI
	Card card7 = new Card("Card7", false, "You can get out of jail ");			///////YAPILDI
	Card card8 = new Card("card8", false, "Random player give you \n500 TL");   ///YAPILDI
	Card card9 = new Card("card9", false, "The earthquake occurs in \none of the cities. "); //////////////YAPILDI
	Card card10 = new Card("card10", false, "It's your birthday,all\n players give you (250TL)");    ///YAPILDI
	Card card11 = new Card("card11", false, "You can  increase rent\n charge of one city (+100TL) randomly."); ////////YAPILDI
	Card card12 = new Card("card12", false, "New Investor!A investor will build \na house one of your city."); ////////YAPILDI
	Card card13 = new Card("card13", false, "Go to Start.");							////////YAPILDI
	Card card14 = new Card("card14", false, "Distraint! You can take a \nproperty that is bought \nlast from random player");///YAPILDI
	Card card15 = new Card("card15", false, "You can take extra 200 TL ");				////YAPILDI
	Card card16 = new Card("card16", false, "Go to Teleport");				             ////YAPILDI
	Card card17 = new Card("card17", false, "Roll dice again.");						///YAPILDI
	Card card18 = new Card("card18", false, "Go to Start.");                           ///// YAPILDI
	Card card19 = new Card("card19", false, "Roll dice again.");						////YAPILDI
	Card card20 = new Card("card20", false, "Say no. You will \nnot pay the rent after this."); //////////YAPILDI

	public CardDeck() {
		cardArray.add(card1);
		cardArray.add(card2);
		cardArray.add(card3);
		cardArray.add(card4);
		cardArray.add(card5);
		cardArray.add(card6);
		cardArray.add(card7);
		cardArray.add(card8);
		cardArray.add(card9);
		cardArray.add(card10);
		cardArray.add(card11);
		cardArray.add(card12);
		cardArray.add(card13);
		cardArray.add(card14);
		cardArray.add(card15);
		cardArray.add(card16);
		cardArray.add(card17);
		cardArray.add(card18);
		cardArray.add(card19);
		cardArray.add(card20);

	}

	public Card drawCard(User user, ArrayList<User> UserArray) {
		int selectedCard = (int) (Math.random() * 20 + 1);
		System.out.println(cardArray.get(selectedCard - 1).toString());
		if (cardArray.get(selectedCard - 1).equals(card1)) {
			c1(user);
		} else if (cardArray.get(selectedCard - 1).equals(card2)) {
			c2(user);
		} else if (cardArray.get(selectedCard - 1).equals(card3)) {
			c3(user);
		} else if (cardArray.get(selectedCard - 1).equals(card3)) {
			c3(user);
		} else if (cardArray.get(selectedCard - 1).equals(card3)) {
			c3(user);
		} else if (cardArray.get(selectedCard - 1).equals(card4)) {
			c4(user);
		} else if (cardArray.get(selectedCard - 1).equals(card5)) {
			c5(user);
		} else if (cardArray.get(selectedCard - 1).equals(card6)) {
			c6(user);
		} else if (cardArray.get(selectedCard - 1).equals(card7)) {
			c7(user);
		} else if (cardArray.get(selectedCard - 1).equals(card8)) {




			c8(user, UserArray );
		} else if (cardArray.get(selectedCard - 1).equals(card9)) {
			c9(user,UserArray);
		} else if (cardArray.get(selectedCard - 1).equals(card10)) {

			c10(user, UserArray);
		} else if (cardArray.get(selectedCard - 1).equals(card11)) {
			c11(user);

		} else if (cardArray.get(selectedCard - 1).equals(card12)) {
			c12(user);
		} else if (cardArray.get(selectedCard - 1).equals(card13)) {
			c13(user);
		} else if (cardArray.get(selectedCard - 1).equals(card14)) {
			c14(user, UserArray);
		} else if (cardArray.get(selectedCard - 1).equals(card15)) {
			c15(user);
		} else if (cardArray.get(selectedCard - 1).equals(card16)) {
			c16(user);
		} else if (cardArray.get(selectedCard - 1).equals(card17)) {
			c17(user);
		} else if (cardArray.get(selectedCard - 1).equals(card18)) {
			c18(user);
		} else if (cardArray.get(selectedCard - 1).equals(card19)) {
			c19(user);
		} else if (cardArray.get(selectedCard - 1).equals(card20)) {
			c20(user);
		}

		return (Card)cardArray.get(selectedCard - 1);

	}

	public void c1(User User) {

		User.setMoney(User.getMoney() - 200);

	}

	public void c2(User User) {

	User.setDoubleRent(true);

	}

	public void c3(User User) {
	///done
		User.getUserPos().x=148;
		User.getUserPos().y=129;
		User.setUserX(0);
		User.setUserY(0);
		User.setJailCount(User.getJailCount()+3);
		User.setInTheJail(true);

	}

	public void c4(User User) {
		User.getUserPos().x=148;
		User.getUserPos().y=721;
		User.setUserX(0);
		User.setUserY(8);

	}

	public void c5(User User) {
		User.setMoney(User.getMoney() + 1000);
	}

	public void c6(User User) {

		User.setMoney(User.getMoney() - 200);
	}

	public void c7(User user) {
		user.setJailCount(user.getJailCount()-3);
	}

	public void c8(User user, ArrayList<User> users) {
	int random=(int) (Math.random()*users.size()+1)-1;

	while(users.get(random).equals(user)){
			random=(int) (Math.random()*users.size()+1)-1;
		}
		user.setMoney(user.getMoney() + 500);
		users.get(random).setMoney(users.get(random).getMoney()-500);
	}

	public void c9(User user, ArrayList<User> users) {

		int a = (int) (Math.random() * users.size() + 1)-1;
		int b = (int) (users.get(a).getCities().size() * Math.random() + 1-1);
		if (users.get(a).getCities().size() > 0) {
			users.get(a).getCities().get(b).built(users.get(a).getCities().get(b).getHireCount() - 1);
		}
	}

	public void c10(User user1, ArrayList<User> users) {
		int prize=0;
		for (int i=0;i<users.size();i++){
			if (users.get(i)!=user1)
				users.get(i).setMoney(users.get(i).getMoney()-250);
			prize += 250;
		}

		user1.setMoney(user1.getMoney() + prize);
	}

	public void c11(User user) {
		int b = (int) (Math.random() * user.getCities().size() );
		if (user.getCities().size() > 0) {
			user.getCities().get(b).setHire(user.getCities().get(b).getHire() + 100);
			user.getCities().get(b).built(user.getCities().get(b).getHireCount() + 1);
		}
	}

	public void c12(User user) {
		if(user.getCities().size()>0) {
			int b = (int) (user.getCities().size() * Math.random() );
			user.getCities().get(b).built(user.getCities().get(b).getHireCount()+1);
		}
	}

	public void c13(User user) {
		user.getUserPos().x=769;
		user.getUserPos().y=129;
		user.setUserX(8);
		user.setUserY(0);


	}

	public void c14(User user, ArrayList<User> users) {
		int random=(int) (Math.random()*users.size()+1)-1;

		while(users.get(random).equals(user)){
			random=(int) (Math.random()*users.size()+1)-1;
		}
		if(user.getCities().size()>0 && users.get(random).getCities().size()>0) {
			user.getCities().add(users.get(random).getCities().get(users.get(random).getCities().size() - 1));
			users.get(random).getCities().remove(users.get(random).getCities().get(users.get(random).getCities().size() - 1));
		}
	}

	public void c15(User user) {
		user.setMoney(user.getMoney() + 200);
	}

	public void c16(User user) {
		user.getUserPos().x = 148;
		user.getUserPos().y = 721;
		user.setUserX(0);
		user.setUserY(8);




	}

	public void c17(User user) {
		/////// DONE IN PLAYSCREEN////////////


	}

	public void c18(User user) {
		user.getUserPos().x=769;
		user.getUserPos().y=129;
		user.setUserX(8);
		user.setUserY(0);
	}

	public void c19(User user) {
		/////// DONE IN PLAYSCREEN////////////


	}

	public void c20(User user) {
		user.setPayRent(false);

	}

	public static ArrayList getCardArray() {
		return cardArray;
	}

}
