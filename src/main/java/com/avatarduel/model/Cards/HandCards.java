package com.avatarduel.model.Cards;

import com.avatarduel.model.Card.Card;

import java.util.LinkedList;
import java.util.List;

public class HandCards {
    private List<Card> cards;

    public HandCards(List<Card> c) {
        cards = new LinkedList<Card>(c);
    }

    public int size() {
        return cards.size();
    }

    public void add(Card newCard) {
        if (cards.size() < 12) {
            cards.add(newCard);
        } //else throw exception
    }

    public Card take(int index) {
        Card takenCard = null;
        if (cards.size() > 0) {
            takenCard = cards.remove(index);
        }
        return takenCard;
    }
}