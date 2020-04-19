package com.avatarduel.model.cards;

import com.avatarduel.model.card.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * HandCards.java
 * A Class that handles Cards that is currently in the hand of a player
 * ATTRIBUTES :
 * List of Card : Cards in the Hand of a player
 */
public class HandCards {
    private List<Card> cards;

    /**
     * Create a new HandCards object that consists of List of Card c
     * @param c List of card
     */
    public HandCards(List<Card> c) {
        cards = new LinkedList<Card>(c);
    }

    /**
     * Return the Size of the Current HandCards cards list
     * @return Current cards Size
     */
    public int size() {
        return cards.size();
    }

    /**
     * Get the Hand Cards of the player
     * @return List of Player's Hand Cards
     */
    public List<Card> getCards() { return cards; }

    /**
     * Add a card to the list of HandCard (Draw Phase)
     * @param newCard card to be added
     */
    public void add(Card newCard) {
        cards.add(newCard);
    }

    /**
     * Take a specified cards and remove it from the HandCards List
     * @param index specified card index of list
     * @return Specified card
     */
    public Card take(int index) {
        Card takenCard = null;
        if (cards.size() > 0 && index < size()) {
            takenCard = cards.remove(index);
        }
        return takenCard;
    }

    /**
     * Take a specified cards from the HandCards List
     * @param index specified card index in HandCards list
     * @return Specified card
     */
    public Card peek(int index) {
        Card takenCard = null;
        if (index >= 0 && index < size())
            takenCard = cards.get(index);
        return takenCard;
    }
}