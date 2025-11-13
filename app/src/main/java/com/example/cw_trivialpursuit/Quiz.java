package com.example.cw_trivialpursuit;

import java.util.Vector;

public class Quiz {
    private final Vector<Card> cards;

    public Quiz() {
        cards = new Vector<>();

        Card c1 = new Card();
        c1.setQuestion("What is the name of the official Android mascot?");
        c1.setRightAnswer("Bugdroid.");
        c1.addWrongAnswer("Andy the Android.");
        c1.addWrongAnswer("RoboBob.");
        c1.addWrongAnswer("Droidy.");
        c1.addWrongAnswer("IronBot.");
        cards.add(c1);

        Card c2 = new Card();
        c2.setQuestion("To what is every new Android version related?");
        c2.setRightAnswer("To a snack.");
        c2.addWrongAnswer("To a color.");
        c2.addWrongAnswer("To a capital.");
        c2.addWrongAnswer("To an animal.");
        cards.add(c2);

        Card c3 = new Card();
        c3.setQuestion("In what year was the first Android version (Cupcake) released?");
        c3.setRightAnswer("In 2008.");
        c3.addWrongAnswer("In 2009.");
        c3.addWrongAnswer("In 2010.");
        cards.add(c3);
    }
    public Vector<Card> getCards() {
        return cards;
    }
    public int getCardCount() {
        return cards.size();
    }
//    public Card getCards(int index) {
//        return cards.get(index);
//    }
}
