package com.avatarduel.model.card;

/**
 * 
 * Card.java
 * An abstract class that contain a basic information and methods of a card
 * 
 */
public abstract class Card {
    
    protected String name;
    protected String description;
    protected Element element;
    protected String imgPath;

    /**
     * Construct a new card object
     * @param name Name of The Card
     * @param description Description of The Card
     * @param element Element Of The Card
     * @param imgPath Path to the Image of The Card
     */
    public Card(String name, String description, Element element, String imgPath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
    }

    /**
     * Get The Name of the card
     * @return name attribute
     */
    public String getName() { return name; }

    /**
     * Get the description of the card
     * @return description attribute
     */
    public String getDescription() { return description; }

    /**
     * Get the element of the card
     * @return element attribute
     */
    public Element getElement() { return element; }

    /**
     * Get the Image Path of The Card
     * @return imgPath attribute
     */
    public String getImgPath() { return imgPath; }

    /**
     * Abstract method, Get a specific attribute of the card
     * @param attribute specified attribute
     * @return specified attribute of the card (int)
     */
    public abstract int getAttribute(Attribute  attribute);

    /**
     * Abstract method, Set the value of a specific attribute of the card
     * @param attribute specified attribute
     * @param value desired value of specified attribute
     */
    public abstract void setAttribute(Attribute  attribute, int value);
}
