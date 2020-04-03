package com.avatarduel;

import com.avatarduel.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int HP;
    private int waterPower;
    private int firePower;
    private int airPower;
    private int earthPower;
    private List<Card> deck;
    private  List<Card> cardsInHand;

    public Player(List<Card> cards) {
        this.HP = 80;
        cardsInHand = new ArrayList<Card>(cards.subList(0, 7)); //get 7 elements from cards
        deck = new ArrayList<Card>(cards.subList(7, cards.size()));
//        System.out.println("Check cardsInHand & deck (class Player) " + cardsInHand.size() + " " + deck.size());
    }

    public void takeCardFromDeck() {
        if (cardsInHand.size() < 12) { //max 12 kartu
            cardsInHand.add(deck.get(0));
            deck.remove(0);
        } // bikin exception (?)
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public int getWaterPower() {
        return waterPower;
    }

    public void setWaterPower(int waterPower) {
        this.waterPower = waterPower;
    }

    public int getFirePower() {
        return firePower;
    }

    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }

    public int getAirPower() {
        return airPower;
    }

    public void setAirPower(int airPower) {
        this.airPower = airPower;
    }

    public int getEarthPower() {
        return earthPower;
    }

    public void setEarthPower(int earthPower) {
        this.earthPower = earthPower;
    }
}
