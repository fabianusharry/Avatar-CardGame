package com.avatarduel.model.field;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.util.Constants;

/**
 * 
 * SkillField.java
 * A Class that manage the Skill Field of a Player
 * ATTRIBUTES:
 * field Array Of Skill : Consists of the Number of Skills that a player currently summon on their field
 * 
 */
public class SkillField {
    Skill[] field;

    /**
     * Construct a New Skill Field with nFieldInSkillField (6) size
     */
    public SkillField() {
        field = new Skill[Constants.nFieldInSkillField];
    }

    /**
     * Place the Skill Card Into the desired pane (represented by index)
     * @param index the array index (also represent the pane index) of field that the will be assigned by the card
     * @param card the Skill Card that will be placed
     */
    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = (Skill) card;
        } // else throw exception (?)
    }

    /**
     * Remove a Skill Card From a specified Index of the Field
     * @param index the field index of Character card that will be removed
     * @return The Skill Card that is removed from the field
     */
    public Card removeCard(int index) {
        Card result = field[index];
        field[index] = null;
        return result;
    }

    /**
     * Get Specific Skill Card From the Field
     * @param index the field array index
     * @return the Skill Card in specified index of the field
     */
    public Card getCard(int index) {
        return field[index];
    }
}