package com.avatarduel.model;

import com.avatarduel.exceptions.hand.InsufficientPowerException;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.cards.Deck;
import com.avatarduel.model.cards.HandCards;
import com.avatarduel.model.field.CardField;
import com.avatarduel.util.Constants;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Player.java
 * A class to initiate the player of the game and all of its attributes
 * ATTRIBUTES :
 * Name                     : The Player Name
 * HP                       : Player Health Points
 * Current and Max Power    : Current Power the Player have and Max Power Allowed
 * Deck of Cards            : The Player Deck
 * Hand Cards               : The Player Hand Cards
 * Player Field             : The Field on which the Player Place the card
 */
public class Player {
    private String name;
    private int HP;
    private Power powerNow;
    private Power maxPower;
    private Deck deck;
    private HandCards handCards;
    public CardField field;

    /** Creates a new Player
     * @param name the player name
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     * NOTE : 
     * The player attributes attributes is initiated and created in each of the class and
     * then assigned to the Player attribute
     * For Example :
     * the player deck is initiated in Deck Class
     * the player Field is initiated in CardField Class 
     */
    public Player(String name) throws IOException, URISyntaxException {
        this.name = name;
        this.HP = Constants.playerInitialHP;
        powerNow = new Power();
        maxPower = new Power();
        deck = new Deck();
        handCards = new HandCards(deck.takes(Constants.playerInitialCard));
        field = new CardField();
    }

    /**
     * Get the value of HP Attribute
     * @return integer, represents the HP Value
     */
    public int getHP() {
        return HP;
    }

    /**
     * Get the Player's Current Attribute
     * @return HandCards
     */
    public HandCards getHandCards() {
        return handCards;
    }

    /**
     * Get the Player's current Deck
     * @return Deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Get The name of the player
     * @return String, represents the Player Name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get Player's Current Power
     * @return Power
     */
    public Power getPowerNow() {
        return powerNow;
    }

    /**
     * Get Player's Maximum Power Allowed
     * @return Power
     */
    public Power getMaxPower() {
        return maxPower;
    }

    /** 
     * Set HP attributes to a specific value
     * @param HP the desired value of player HP
    */
    public void setHP(int HP) {
        if (HP <= 0) {
            HP = 0;
        }
        this.HP = HP;
    }

    /**
     * Reduce HP attribute of a player by a specific value
     * @param value the desired HP reduction value 
     */
    public void reduceHP(int value) {
        setHP(HP-value);
    }

    /**
     * Draw a card from the deck into the player hand
     * NOTE : Implemented by using a method from HandCards Class
     */
    public void draw() {
        if (this.getHandCards().size() < Constants.maxCardOnHand) {
            handCards.add(deck.take());
        }
    }

    /**
     * Take a specific card from HandCards, change the attribute required (powerNow and maxPower)
     * @param index Card Index in handCards List
     * @throws InsufficientPowerException exception when player doesn't have enough power to take the desired card
     * @return Card, the card taken
     */
    public Card takeCard(int index) throws InsufficientPowerException {
        Card takenCard = null;
        if (index >= 0 && index < handCards.size()) {
            if (handCards.peek(index) instanceof com.avatarduel.model.card.Land) {
                takenCard = handCards.take(index);
                powerNow.add(takenCard.getElement());
                maxPower.add(takenCard.getElement());
            } else {
                if (powerNow.get(handCards.peek(index).getElement()) >= handCards.peek(index).getAttribute(Attribute.POWER)) {
                    takenCard = handCards.take(index);
                    Element takenCardElement = takenCard.getElement();
                    powerNow.set(takenCardElement, powerNow.get(takenCardElement) - takenCard.getAttribute(Attribute.POWER));
                } else {
                    throw new InsufficientPowerException(handCards.peek(index).getElement());
                }
            }
        }
        return takenCard;
    }

    /**
     * Reset The Player's Current Power
     * NOTE : Implemented by constructing a new Power
     */
    public void resetPowerNow() {
        powerNow = new Power(maxPower);
    }
}
