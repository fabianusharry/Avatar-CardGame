package com.avatarduel.model;

import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Cards.Deck;
import com.avatarduel.model.Cards.HandCards;
import com.avatarduel.model.Field.CardField;

import java.io.IOException;
import java.net.URISyntaxException;

public class Player {
    private String name;
    private int HP;
    private Power power;
    private Deck deck;
    private HandCards handCards;
    public CardField field;

    public Player(String name) throws IOException, URISyntaxException {
        this.name = name;
        this.HP = 80;
        power = new Power();
        deck = new Deck();
        handCards = new HandCards(deck.takes(7));
        field = new CardField();
    }

    public int getHP() {
        return HP;
    }
    public HandCards getHandCards() { return handCards; }
    public String getName() { return name; }

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
        Card takenCard = handCards.take(index);
        if (takenCard instanceof com.avatarduel.model.Card.Land) {
            power.add(takenCard.getElement());
        }
        return takenCard;
    }
}
