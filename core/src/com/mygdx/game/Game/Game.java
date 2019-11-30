package com.mygdx.game.Game;
import java.util.Scanner;
import UI.UI;

public class Game {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		UI u1 = new UI();
		u1.start();
		
		while(true) {
			System.out.println("Command");
			String x = scan.next();
			if (x.equals("roll"))
				u1.dieRoll();
			if(x.equals("move")) {
				System.out.println("Nereye");
				int y = scan.nextInt();
				u1.User1.setMove(y);
			}
			else if (x.equals("roll")) {
				u1.dieRoll();
			}
			else {
				u1.changeScene(x);
			}
			if (x.equals("start"))
				u1.changeScene("start");
			else if (x.equals("board"))
				u1.changeScene("board");
		}
	}

}
