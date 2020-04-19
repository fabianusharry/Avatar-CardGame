package com.avatarduel.model.cards;

import com.avatarduel.model.card.Card;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void take() throws Exception {
        Deck deck = new Deck();
        int prevSize = deck.size();
        Card card = deck.take();

        // deck size should decreased by one
        assertEquals(deck.size(), prevSize-1);
    }

    @Test
    public void takes() throws Exception {
        Deck deck = new Deck();
        int prevSize = deck.size();
        int n = 10;

        List<Card> card = deck.takes(n);

        // deck size should decreased by n
        assertEquals(deck.size(), prevSize-n);
    }
}