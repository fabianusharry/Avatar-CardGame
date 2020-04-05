package com.avatarduel.model.Player;

import com.avatarduel.model.Card;
import com.avatarduel.model.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private int HP;
    private Map<Element, Integer> power;
    private Deck deck;
    private HandCards handCards;

    public Player(List<Card> cards) {
        this.HP = 80;
        
        // Build deck and handCards
        deck = new ArrayList<Card>(cards.subList(7, cards.size()));
        cardsInHand = new ArrayList<Card>(cards.subList(0, 7)); 
        
        // Initialize powers
        power = new HashMap<>();
        power.put(Element.AIR, 0);
        power.put(Element.FIRE, 0);
        power.put(Element.EARTH, 0);
        power.put(Element.WATER, 0);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP < 0) {
            HP = 0;
        }
        this.HP = HP;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public void takeCardFromDeck() {
        if (cardsInHand.size() < 12) { //max 12 kartu
            cardsInHand.add(deck.get(0));
            deck.remove(0);
        } // bikin exception (?)
    }

    public List<Card> getCardsInHand() {
        return cardsInHand; //buat debugging aja
    }

    public Card takeCardFromHand(int index) {
        if (cardsInHand.get(index) instanceof com.avatarduel.model.Land) { //kalo Land tambah power
            Element e = cardsInHand.get(index).getElement();
            power.put(e, power.get(e)+1);
        }
        return cardsInHand.remove(index);
    }

    public int getPower(Element element) {
        return power.get(element);
    }

    public void resetPower() {

    }

}
