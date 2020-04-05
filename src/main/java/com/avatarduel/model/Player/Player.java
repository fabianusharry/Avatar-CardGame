package com.avatarduel.model.Player;

import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Cards.Cards;
import com.avatarduel.model.Cards.Deck;
import com.avatarduel.model.Cards.HandCards;
import com.avatarduel.model.Card.Element;
import com.avatarduel.model.Power;

import java.util.List;

public class Player {

    private int HP;
    private Power power;
    private Deck deck;
    private HandCards handCards;

    public Player(List<Card> cards) {
        this.HP = 80;
        power = new Power();
        handCards = new HandCards(cards.subList(0, 7));
        deck = new Deck(cards.subList(7, cards.size()));
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

    public Card takeCardFromHand(int index) {
        Card takenCard = handCards.take();
        if (takenCard instanceof com.avatarduel.model.Card.Land) { //kalo Land tambah power
            power.add(takenCard.getElement());
        }
        return takenCard;
    }
}
