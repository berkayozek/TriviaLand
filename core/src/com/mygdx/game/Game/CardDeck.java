package com.mygdx.game.Game;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;

public class CardDeck {
	//TODO KARTLARDA ELİNDE TUTULACAKLAR AYARLANACAK, TELEPORT YERİ HESAPLANACAK
///////////////ELİNDE TUTULAMAYAN KARTLARTI SİLMEYİ DENE//////////////////
	ArrayList cardArray = new ArrayList<Card>();
	Card card1 = new Card("Card1", false, "You have to pay bills.(200TL)");
	Card card2 = new Card("Card2", true, "You will pay double rent in the next city.");
	Card card3 = new Card("Card3", false, "Go to Jail.");
	Card card4 = new Card("Card4", false, "Go to Teleport");
	Card card5 = new Card("Card5", false, "You win lottery.Get your money from bank.");
	Card card6 = new Card("Card6", false, "You have to pay bills.(200TL)");
	Card card7 = new Card("Card7", true, "You can get out of jail ");
	Card card8 = new Card("card8", false, "Force any player to give you 500 TL");
	Card card9 = new Card("card9", false, "You can demolish one hotel ");
	Card card10 = new Card("card10", false, "It's your birthday,all players give you (250TL)");
	Card card11 = new Card("card11", false, "You can  increase rent charge of one city (+100TL)");
	Card card12 = new Card("card12", false, "You can build one house to one of your cities");
	Card card13 = new Card("card13", false, "Go to Start.");
	Card card14 = new Card("card14", false, "You can steal a property from the player of your choice");
	Card card15 = new Card("card15", false, "You can take extra 200 TL ");
	Card card16 = new Card("card16", false, "Go to Teleport");
	Card card17 = new Card("card17", false, "Roll dice again.");
	Card card18 = new Card("card18", false, "Go to Start.");
	Card card19 = new Card("card19", false, "Roll dice again.");
	Card card20 = new Card("card20", true, "Say no. You are able to don't pay the rent once.");

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
			User selectedUser=null;
			int i=(int)(Math.random()*(UserArray.size()+1));

			while(UserArray.get(i).equals(user)){
				selectedUser = (User)UserArray.get(i);
			}



			c8(user,selectedUser );
		} else if (cardArray.get(selectedCard - 1).equals(card9)) {
			c9(user);
		} else if (cardArray.get(selectedCard - 1).equals(card10)) {
			///////// User degisecek/////////////////////////////////////////
			User otherUser1 = null;
			User otherUser2 = null;

			User otherUser3 = null;
			c10(user, otherUser1, otherUser2, otherUser3);
		} else if (cardArray.get(selectedCard - 1).equals(card11)) {
			c11(user);

		} else if (cardArray.get(selectedCard - 1).equals(card12)) {
			c12(user);
		} else if (cardArray.get(selectedCard - 1).equals(card13)) {
			c13(user);
		} else if (cardArray.get(selectedCard - 1).equals(card14)) {
			c14(user);
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
		System.out.println();
		card2.setUser(User);

	}

	public void c3(User User) {

		User.getUserPos().x=156;
		User.getUserPos().y=129;
		User.setUserX(0);
		User.setUserY(0);
		User.setJailCount(3);

	}

	public void c4(User User) {
		User.getUserPos().x=156;//teleport yeri hesaplanacak
		User.getUserPos().y=129;// teleport yeri hesaplanacak
		User.setUserX(0);
		User.setUserY(8);

	}

	public void c5(User User) {
		System.out.println();

		User.setMoney(User.getMoney() + 1000);

	}

	public void c6(User User) {

		User.setMoney(User.getMoney() - 200);
	}

	public void c7(User user) {
		card7.setUser(user);
		card7.getUser().getCards().add(card7);
	}

	public void c8(User user, User user2) {

		user.setMoney(user.getMoney() + 500);
		user2.setMoney(user2.getMoney() - 500);
	}

	public void c9(User user) {

		////// BUTONLARDAN SONRA/////////////
	}

	public void c10(User user1, User user2, User user3, User user4) {

		user1.setMoney(user1.getMoney() + 1000);
		user2.setMoney(user2.getMoney() - 250);
		user3.setMoney(user3.getMoney() - 250);
		user4.setMoney(user4.getMoney() - 250);

	}

	public void c11(User user) {

		///////// BUTONLARDAN SONRA//////////////

	}

	public void c12(User user) {
		///////////// BUTONLARDAN SONRA//////////////////
	}

	public void c13(User user) {
/*
		user.setUserX(8);
		user.setUserY(8);
		user.setMoveCount(1);
		user.setMove(1);
*/
	}

	public void c14(User user) {
		////////////// BUTONLARDAN SONRA/////////////////////
	}

	public void c15(User user) {
		user.setMoney(user.getMoney() + 200);
	}

	public void c16(User user) {
		user.setPos(9);
	}

	public void c17(User user) {
		/////// MOVE VE ROLL TEKRAR YAPTIRILACAK///////////////

	}

	public void c18(User user) {
		//user.setUserX(8);
		//user.setUserY(8);
		//user.setMoveCount(1);
		//user.setMove(1);
	}

	public void c19(User user) {
		/////// MOVE VE ROLL TEKRAR YAPTIRILACAK///////////////

	}

	public void c20(User user) {
		card20.setUser(user);
		user.getCards().add(card20);

		//////////////// METHODLAR EKLENECEK//////////////////////////

	}

}
