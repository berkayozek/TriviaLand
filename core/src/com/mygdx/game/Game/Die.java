package com.mygdx.game.Game;

    public class Die {

        private int die1;
        private int die2;

        public Die() {

            die1 = (int) (Math.random() * 6 + 1);

            die2 = (int) (Math.random() * 6 + 1);

        }

        public void roll() {

            die1 = (int) (Math.random() * 6 + 1);

            die2 = (int) (Math.random() * 6 + 1);
        }

        public int getDie1() {

            return die1;
        }

        public int getDie2() {

            return die2;
        }

        public int getSum() {

            return die1 + die2;
        }

    }

