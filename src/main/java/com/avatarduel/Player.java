package com.avatarduel;

import com.avatarduel.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int HP;
    private List<Card> deck;
    private  List<Card> cardsInHand;


    public Player(List<Card> cards) {
        this.HP = 80;
        cardsInHand = new ArrayList<Card>(cards.subList(0, 7)); //get 7 elements from cards
        deck = new ArrayList<Card>(cards.subList(7, cards.size()));
        System.out.println("Check cardsInHand & deck (class Player) " + cardsInHand.size() + " " + deck.size());
    }

}
