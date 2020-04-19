package com.avatarduel.model.card.effect;

import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.Player;

/**
 * 
 * Aura.java
 * Subclass of skill that contains informations and methods of Aura Skill Cards
 * 
 */
public class Aura extends Skill {
    
    private int attack;
    private  int defense;

    /**
     * Construct a new Aura Skill Card
     * @param name Name of the Card
     * @param description Description of the card
     * @param element Element of the card
     * @param attack Attack value of the card
     * @param defense Defense value of the card
     * @param power Power value of the card
     * @param imgPath Path to the image of the card
     * NOTE : Aura is constructed by calling a super method to construct a Skill Card first
     */
    public Aura(String name, String description, Element element, int attack, int defense, int power, String imgPath) {
        super(name, description, element, power, imgPath);
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * Get the attack value of the card
     * @return attack
     */
    public int getAttack() { return attack; }

    /**
     * Get the defense value of the card
     * @return defense
     */
    public int getDefense() { return defense; }


    /**
     * Activate the Aura card
     * @param destination Player that is effected by the skill
     * @param index Specified Card index in the field
     * @param location Target Location
     */
    @Override
    public void activate(Player destination, int index,String location) {
        Card destinationCard = destination.field.getCharacterField().getCard(index).getCharacter();
        int newAttack = destinationCard.getAttribute(Attribute.ATTACK)+this.attack;
        int newDefense = destinationCard.getAttribute(Attribute.DEFENSE)+this.defense;
        destinationCard.setAttribute(Attribute.ATTACK, newAttack);
        destinationCard.setAttribute(Attribute.DEFENSE, newDefense);
        
        destination.field.getCharacterField().attachSkill(index, this,location);
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
        return  result;
    }
}
