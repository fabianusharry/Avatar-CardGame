package com.avatarduel.model.card.effect;

import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.Player;

/**
 * 
 * Destroy.java
 * Subclass of skill that contains informations and methods of Destroy Skill Cards
 * 
 */
public class Destroy extends Skill {

    /**
     * Construct a Destroy Skill Card
     * @param name Name of the card
     * @param description Description of the card
     * @param element Element of the card
     * @param power Power of the card
     * @param imgPath Path to the image of the card
     * NOTE : Destroy Skill Card is constructed by calling a super method to construct a Skill Card first
     */
    public Destroy(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, power, imgPath);
    }
    
    /**
     * Activate the Destroy card
     * @param destination Player that is effected by the skill
     * @param index Specified Card index in the field
     * @param location Target Location
     */
    @Override
    public void activate(Player destination, int index,String location) {
        SummonedCard destinationCard = destination.field.getCharacterField().removeCard(index);
    }
}
