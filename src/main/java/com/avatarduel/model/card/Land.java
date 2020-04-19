package com.avatarduel.model.card;

/**
 * 
 * Land.java
 * Subclass of Card that contains Information and Methods of Land Card
 * 
 */
public class Land extends Card {

    /**
     * Construct a Land Card
     * @param name Name of The Card
     * @param description Description of the Card
     * @param element Element of the Card
     * @param imgPath Path to image of the card
     * NOTE : Land Card is constructed by calling a super method to construct a card
     */
    public Land(String name, String description, Element element, String imgPath) {
        super(name, description, element, imgPath);
    }

    /**
     * Get the value of a specified attribute of the card
     * @param attribute specified attribute
     * @return none
     * NOTE : You can not get anything from Land Card Attributes
     */
    @Override
    public int getAttribute(Attribute attribute) { return -1; }

    /**
     * Set the value of a specified attribute of the card
     * NOTE : Nothing needs to be set on the Land Cards
     */
    @Override
    public void setAttribute(Attribute attribute, int value) { } // attribut land card tidak ada yang perlu di set
}