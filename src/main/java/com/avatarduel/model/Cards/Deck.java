package com.avatarduel.model.Cards;

import com.avatarduel.model.Card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Deck implements Cards {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<Card>();
    }

    public Deck(List<Card> c) {
        this();
        cards.addAll(c);
    }

    public int size() {
        return cards.size();
    }

    public void add(Card newCard) {
        if (cards.size() < 60) {
            cards.push(newCard);
        } //else throw exception
    }

    public Card take() {
        Card takenCard = null;
        if (cards.size() > 0) {
            takenCard = cards.pop();
        } //else throw exception (END GAME)
        return takenCard;
    }
}