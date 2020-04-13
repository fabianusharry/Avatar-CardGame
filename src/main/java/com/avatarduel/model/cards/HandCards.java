package com.avatarduel.model.cards;

import com.avatarduel.model.card.Card;

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
    public List<Card> getCards() { return cards; }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public Card take(int index) {
        Card takenCard = null;
        if (cards.size() > 0) {
            takenCard = cards.remove(index);
        }
        return takenCard;
    }

    public Card peek(int index) {
        Card takenCard = null;
        if (index >= 0 && index < size())
            takenCard = cards.get(index);
        return takenCard;
    }
}