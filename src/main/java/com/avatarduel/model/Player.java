package com.avatarduel.model;

import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Cards.Deck;
import com.avatarduel.model.Cards.HandCards;
import com.avatarduel.model.Power;

import java.io.IOException;
import java.net.URISyntaxException;

public class Player {
    private int HP;
    private Power power;
    private Deck deck;
    private HandCards handCards;

    public Player() throws IOException, URISyntaxException {
        this.HP = 80;
        power = new Power();
        deck = new Deck();
        handCards = new HandCards(deck.takes(7));
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP <= 0) {
            HP = 0; //atau throw exception (END GAME)
        }
        this.HP = HP;
    }

    public void draw() {
        handCards.add(deck.take());
    }

    public Card takeCard(int index) {
        Card takenCard = handCards.take();
        if (takenCard instanceof com.avatarduel.model.Card.Land) { //kalo Land tambah power
            power.add(takenCard.getElement());
        }
        return takenCard;
    }
}
