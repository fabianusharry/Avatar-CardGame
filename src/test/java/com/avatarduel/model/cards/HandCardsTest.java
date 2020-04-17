package com.avatarduel.model.cards;

import com.avatarduel.model.card.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandCardsTest {

    @Test
    public void construct() throws Exception {
        Deck deck = new Deck();
        int n = 10;
        HandCards cards = new HandCards(deck.takes(n));

        // size should be n
        assertEquals(cards.size(), n);
    }

    @Test
    public void add() throws Exception {
        Deck deck = new Deck();
        int n = 10;
        HandCards cards = new HandCards(deck.takes(n));

        Card card1 = deck.take();
        Card card2 = deck.take();
        cards.add(card1);
        cards.add(card2);

        // size should increase two
        assertEquals(cards.size(), n+2);
    }

    @Test
    public void take() throws Exception {
        Deck deck = new Deck();
        int n = 10;
        HandCards cards = new HandCards(deck.takes(n));

        Card card = cards.take(2);

        // size should decrease one
        assertEquals(cards.size(), n-1);
        assertNotNull(card);
    }

    @Test
    public void peek() throws Exception {
        Deck deck = new Deck();
        int n = 10;
        HandCards cards = new HandCards(deck.takes(n));

        Card card = cards.peek(2);

        // size should not decrease
        assertEquals(cards.size(), n);
        assertNotNull(card);
    }
}