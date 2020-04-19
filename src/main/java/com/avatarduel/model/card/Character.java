package com.avatarduel.model.card;

/**
 * 
 * Character.java
 * Subclass of Card that Contains Informations and method of Character Card
 * 
 */
public class Character extends Card {

    protected int attack;
    protected int defense;
    protected int power;

    /**
     * Construct a new Character Card
     * @param name Name of the Card
     * @param description Description of the card
     * @param element Element of the card
     * @param attack Attack value of the card
     * @param defense Defense value of the card
     * @param power Power value of the card
     * @param imgPath Path to the image of the card
     * NOTE : Character is constructed by calling a super method to construct a card first
     */
    public Character(String name, String description, Element element, int attack, int defense, int power, String imgPath) {
        super(name, description, element, imgPath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    /**
     * Get the value of a specified attribute of the card
     * @param attribute specified attribute
     * @return the value of specified attribute
     */
    @Override
    public int getAttribute(Attribute attribute) {
        int result = -1;
        if (attribute == Attribute.ATTACK) {
            result = attack;
        } else if (attribute == Attribute.DEFENSE) {
            result = defense;
        } else if (attribute == Attribute.POWER) {
            result = power;
        }
        return result;
    }

    /**
     * Set the value of a specified attribute of the card
     * @param attribute specified attribute
     * @param value the desired value of specified attribute
     */
    @Override
    public void setAttribute(Attribute  attribute, int value) {
        if (attribute == Attribute.ATTACK) {
            attack = value;
        } else if (attribute == Attribute.DEFENSE) {
            defense = value;
        }
    }
}
