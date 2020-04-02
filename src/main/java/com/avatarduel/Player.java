package com.avatarduel;

import com.avatarduel.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int HP;
    private List<Card> deck;
    private  List<Card> cardsInHand;

    public Player() {
        this.HP = 80;
        deck = new ArrayList<>();
        cardsInHand = new ArrayList<>();
    }


}
