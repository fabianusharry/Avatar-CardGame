package com.avatarduel.model.card.effect;

import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.Player;

/**
 * 
 * PowerUp.java
 * Subclass of skill that contains informations and methods of Power Up Skill Cards
 * 
 */
public class PowerUp extends Skill {
    
    /**
     * Construct a Power Up Skill Card
     * @param name Name of the card
     * @param description Description of the card
     * @param element Element of the card
     * @param power Power of the card
     * @param imgPath Path to the image of the card
     * NOTE Power Up Skill Card is constructed by calling a super method to construct a Skill Card first
     */
    public PowerUp(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, power, imgPath);
    }

    /**
     * Activate the Power Up card
     * @param destination Player that is effected by the skill
     * @param index Specified Card index in the field
     * @param location Target Location
     */
    @Override
    public void activate(Player destination, int index, String location) {
        boolean isAttackMode = destination.field.getCharacterField().isAttackMode(index);
        if (!isAttackMode) {
            destination.field.getCharacterField().changeCardPosition(index);
        }

        destination.field.getCharacterField().attachSkill(index, this,location);
    }
}
