package com.avatarduel.model.cards;

import com.avatarduel.pack.Pack;
import com.avatarduel.model.card.Card;
import com.avatarduel.pack.builder.PackBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Deck {
    private Stack<Card> cards;

    public Deck() throws IOException, URISyntaxException {
        cards = new Stack<Card>();
        int nDeck = new Random().nextInt(21) + 40; //random nDeck 40-60
        int nCharacter, nLand, nSkill; // land : character : skill = 2 : 2 : 1

        Pack pack = new PackBuilder().addAllCards().build();
        pack.shuffle();

        nLand = nCharacter = 2*nDeck/5;
        nSkill = nDeck - (nLand + nCharacter);
        cards.addAll(pack.getLands().getCards().subList(0, nLand));
        cards.addAll(pack.getCharacters().getCards().subList(0, nCharacter));
        cards.addAll(pack.getSkills().getCards().subList(0, nSkill));
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card take() {
        Card takenCard = null;
        if (cards.size() > 0) {
            takenCard = cards.pop();
        }
        return takenCard;
    }

    public List<Card> takes(int nCard) {
        List<Card> takenCards = new LinkedList<>();
        for (int i = 0; i < nCard; i++) {
            takenCards.add(this.take());
        }
        return takenCards;
    }
}