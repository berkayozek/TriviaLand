package com.mygdx.game.Game;

public class Board {
        private int board[][] = new int [9][9];

        public Board() {

            for (int k=0;k<9;k++) {
                board[k][0] = k+1;
                board[8][k] = k+9;
                board[k][8] = 25-k;
                if(k!=0)
                    board[0][k] = 33-k;
            }

        }

        public int getBoard(int first,int second) {
            return board[first][second];
        }

        public String getBoardValue(int first,int second) {
            return "" + board[first][second];
        }

        public void printAll() {
            for (int k=0;k<9;k++) {
                for (int i=0;i<9;i++)
                    System.out.printf("%-5d",board[i][k]);
                System.out.println();
                System.out.println();
            }
        }
    }
