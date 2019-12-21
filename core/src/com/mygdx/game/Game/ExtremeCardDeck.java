
package com.mygdx.game.Game;

import java.util.ArrayList;

public class ExtremeCardDeck {



		private static ArrayList extremeCardArray = new ArrayList<ExtremeCard>();

	ExtremeCard ecd1 = new ExtremeCard("Card1", false, " You will go to jail or teleport randomly. ");
	ExtremeCard ecd2 = new ExtremeCard("Card2", true, "You  increase rent of all properties of that you have  fifty percent ");////YAPILDI   İYİ
	ExtremeCard ecd3 = new ExtremeCard("Card3", false, "You win fifty percent of your money");        /////YAPILDI    İYİ
	ExtremeCard ecd4 = new ExtremeCard("Card4", false, " You swap your money with the person you want");
	ExtremeCard ecd5 = new ExtremeCard("Card5", false, "You win lottery.Get your money from bank.(10.000TL)"); ////YAPILDI İYİ
	ExtremeCard ecd6 = new ExtremeCard("Card6", false, "You lost fifty percent of money.");    /////////YAPILDI KÖTÜ
	ExtremeCard ecd7 = new ExtremeCard("Card7", true, "Decrease rent of all properties that you have fifty percent.");		////////YAPILDI KÖTÜ
	ExtremeCard ecd8 = new ExtremeCard("Card6", false, "You lost fifty percent of money.");				///////// YAPILDI    KÖTÜ

	public ExtremeCardDeck() {

		extremeCardArray.add(ecd1);
		extremeCardArray.add(ecd2);
		extremeCardArray.add(ecd3);
		extremeCardArray.add(ecd4);
		extremeCardArray.add(ecd5);
		extremeCardArray.add(ecd6);
		extremeCardArray.add(ecd7);
		extremeCardArray.add(ecd8);

	}

	public ExtremeCard drawExtremeCard(User user,ArrayList<User> UserArray) {
		int selectedCard = (int) (Math.random() * 8 + 1);
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
		} else if (extremeCardArray.get(selectedCard - 1).equals(ecd8)) {
			ecd8(user);
		}
		return	(ExtremeCard) extremeCardArray.get(selectedCard-1);
	}

	public void ecd1(User user) {
		//TODO Hapise ya da teleporta
		/////////////////////random bir yere gitme sıkıntılı onun yerine jail ya da hapis yaptım
		int a=(int) (Math.random() * 2+ 1);

		if(a==1){
			user.getUserPos().x=148;
			user.getUserPos().y=129;
			user.setUserX(0);
			user.setUserY(0);
			user.setJailCount(3);

		}
		if(a==2){
			user.getUserPos().x=148;
			user.getUserPos().y=721;
			user.setUserX(0);
			user.setUserY(8);
		}




	/*	if(a==0){
			user.getUserPos().x=769;
			user.getUserPos().y=129;
			user.setUserX(8);
			user.setUserY(0);
		}
		if(a==1){
			user.getUserPos().x= (float) 691.375;
			user.getUserPos().y=129;
			user.setUserX(7);
			user.setUserY(0);
		}

		if(a==2){
			user.getUserPos().x= (float) 613.75;
			user.getUserPos().y=129;
			user.setUserX(6);
			user.setUserY(0);
		}

		if(a==3){
			user.getUserPos().x= (double) 536.125‬;
			user.getUserPos().y=129;
			user.setUserX(5);
			user.setUserY(0);
		}

		if(a==4){
			user.getUserPos().x=(float) 458.5‬‬;
			user.getUserPos().y=129;
			user.setUserX(4);
			user.setUserY(0);
		}

		if(a==5){
			user.getUserPos().x= (float) 380.875‬;
			user.getUserPos().y=129;
			user.setUserX(3);
			user.setUserY(0);
		}

		if(a==6){
			user.getUserPos().x=(float) 303.25‬‬;
			user.getUserPos().y=129;
			user.setUserX(2);
			user.setUserY(0);
		}
		if(a==7){
			user.getUserPos().x=(float) 225.625‬‬;
			user.getUserPos().y=129;
			user.setUserX(1);
			user.setUserY(0);
		}
		if(a==8){/////////////////////////HESAPLANDI
			user.getUserPos().x=148;
			user.getUserPos().y=129;
			user.setUserX(0);
			user.setUserY(0);
			user.setJailCount(3);
		}
		if(a==9){
			user.getUserPos().x=148;
			user.getUserPos().y=203;
			user.setUserX(0);
			user.setUserY(1);
		}
		if(a==10){
			user.getUserPos().x=148;
			user.getUserPos().y=277;
			user.setUserX(0);
			user.setUserY(2);
		}
		if(a==11){
			user.getUserPos().x=148;
			user.getUserPos().y=351;
			user.setUserX(0);
			user.setUserY(3);
		}
		if(a==12){
			user.getUserPos().x=148;
			user.getUserPos().y=425;
			user.setUserX(0);
			user.setUserY(4);
		}
		if(a==13){
			user.getUserPos().x=148;
			user.getUserPos().y=499;
			user.setUserX(0);
			user.setUserY(5);
		}
		if(a==14){
			user.getUserPos().x=148;
			user.getUserPos().y=573;
			user.setUserX(0);
			user.setUserY(6);
		}
		if(a==15){
			user.getUserPos().x=148;
			user.getUserPos().y=647;
			user.setUserX(0);
			user.setUserY(7);
		}
		if(a==16){
			user.getUserPos().x = 148;
			user.getUserPos().y = 721;
			user.setUserX(0);
			user.setUserY(8);
		}

		if(a==17){
			user.getUserPos().x=(float) 225.625;
			user.getUserPos().y=721;
			user.setUserX(1);
			user.setUserY(8);
		}
		if(a==18){
			user.getUserPos().x=(float) 303.25‬‬;
			user.getUserPos().y=721;
			user.setUserX(2);
			user.setUserY(8);
		}
		if(a==19){
			user.getUserPos().x=(float) 380.875‬;
			user.getUserPos().y=721;
			user.setUserX(3);
			user.setUserY(8);
		}
		if(a==20){
			user.getUserPos().x=(float) 458.5‬‬;
			user.getUserPos().y=721;
			user.setUserX(4);
			user.setUserY(8);
		}
		if(a==21){
			user.getUserPos().x=(float) 536.125;
			user.getUserPos().y=721;
			user.setUserX(5);
			user.setUserY(8);
		}
		if(a==22){
			user.getUserPos().x=(float) 613.75;
			user.getUserPos().y=721;
			user.setUserX(6);
			user.setUserY(8);
		}

		if(a==23){
			user.getUserPos().x=(float) 691.375;
			user.getUserPos().y=721;
			user.setUserX(7);
			user.setUserY(8);
		}

		if(a==24){
			user.getUserPos().x=769;
			user.getUserPos().y=721;
			user.setUserX(8);
			user.setUserY(8);
		}

		if(a==25){
			user.getUserPos().x=769;
			user.getUserPos().y=647;
			user.setUserX(8);
			user.setUserY(7);
		}

		if(a==26){
			user.getUserPos().x=769;
			user.getUserPos().y=573;
			user.setUserX(8);
			user.setUserY(6);
		}

		if(a==27){
			user.getUserPos().x=769;
			user.getUserPos().y=499;
			user.setUserX(8);
			user.setUserY(5);
		}

		if(a==28){
			user.getUserPos().x=769;
			user.getUserPos().y=425;
			user.setUserX(8);
			user.setUserY(4);
		}

		if(a==29){
			user.getUserPos().x=769;
			user.getUserPos().y=351;
			user.setUserX(8);
			user.setUserY(3);
		}

		if(a==30){
			user.getUserPos().x=769;
			user.getUserPos().y=277;
			user.setUserX(8);
			user.setUserY(2);
		}
		if(a==31){
			user.getUserPos().x=769;
			user.getUserPos().y=203;
			user.setUserX(8);
			user.setUserY(1);
		}

*/

















	}

	public void ecd2(User user) {

		for (int i = 0; i < user.getCities().size(); i++) {
			user.getCities().get(i).setHire(user.getCities().get(i).getHire() + (user.getCities().get(i).getHire() / 2));

		}
	}

	public void ecd3(User user) {
		user.setMoney(user.getMoney() + user.getMoney() / 2);


	}

	public void ecd4(User user) {

		user.getMoney();

	}

	public void ecd5(User user) {
		user.setMoney(user.getMoney() + 10000);
	}

	public void ecd6(User user) {

		user.setMoney(user.getMoney() / 2);

	}

	public void ecd7(User user) {

		for (int i = 0; i < user.getCities().size(); i++) {
			user.getCities().get(i).setHire(user.getCities().get(i).getHire() / 2);
		}
	}

	public void ecd8(User user) {

		user.setMoney(user.getMoney() / 2);

	}

	public static ArrayList getExtremeCardArray() {
		return extremeCardArray;
	}
}
