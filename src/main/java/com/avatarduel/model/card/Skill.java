package com.avatarduel.model.card;

import com.avatarduel.model.Player;

/**
 * 
 * Skill.java
 * Abstract Subclass of Card that contains informations and methods of Skill Card
 * 
 */
public abstract class Skill extends Card {
   
    protected int power;
    protected String targetLocation;

    /**
     * Construct a Skill Card
     * @param name Name of the card
     * @param description Description of the card
     * @param element Element of the card
     * @param power Power of the card
     * @param imgPath Path to the image of the card
     * NOTE : Skill Card is constructed by calling a super method to construct a card first
     */
    public Skill(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, imgPath);
        this.power = power;
        this.targetLocation = "";
    }

    /**
     * Get the target location attribute of Skill Card
     * @return targetLocation
     */
    public String getTargetLocation(){
        return targetLocation;
    }

    /**
     * Get the power attribute of Skill Card
     * @return power
     */
    public int getPower() { return power; }

    /**
     * Activate the skill card
     * @param destination Player that is effected by the skill
     * @param index Specified Card index in the field
     * @param location Target Location
     */
    public abstract void activate(Player destination, int index,String location);

    /**
     * Get the value of a specified attribute of the card
     * @param attribute specified attribute
     * @return the value of specified attribute
     */
    @Override
    public int getAttribute(Attribute attribute) {
        int result = -1;
        if (attribute == Attribute.POWER) {
            result = power;
        }
        return  result;
    }

    /**
     * Set the value of a specified attribute of the card
     * @param attribute specified attribute
     * @param value the desired value of specified attribute
     */
    @Override
    public void setAttribute(Attribute attribute, int value) { } // atribut Skill card tidak perlu ada yang di set
    
    /**
     * Set the value of Target Location attribute
     * @param targetLocation the desired value of target location attribute
     */
    public void setTargetLocation(String targetLocation){
        this.targetLocation = targetLocation;
    }
}
