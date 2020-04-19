package com.avatarduel.model.field;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;
import com.avatarduel.model.card.Skill;
import com.avatarduel.util.Constants;

/**
 * 
 * CharacterField.java
 * A Class that manage the Character Field of a Player
 * ATTRIBUTES:
 * field Array Of SummonedCard : Consists of the Number of Character that a player currently summon on their field (SummonedCharacter)
 * 
 */
public class CharacterField {
    private SummonedCard[] field;

    /**
     * Construct a New Character Field with nFieldInCharacterField (6) size
     */
    public CharacterField() {
        field = new SummonedCharacter[Constants.nFieldInCharacterField];
    }

    /**
     * Summon the Character Card Into the desired pane (represented by index)
     * @param index the array index (also represent the pane index) of field that the will be assigned by the card
     * @param card the Card that will be summoned
     */
    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = new SummonedCharacter(card, true);
        } // else throw exception (?)
    }

    /**
     * Remove a Character Card From a specified Index of the Field
     * @param index the field index of Character card that will be removed
     * @return The Summoned Character that is removed from the field
     */
    public SummonedCard removeCard(int index) {
        SummonedCard result = field[index];
        field[index] = null;
        return result;
    }

     /**
     * Get Specific Summoned Character From the Field
     * @param index the summoned character array index
     * @return the Summoned Character in specified index of the field
     */
    public SummonedCard getCard(int index) {
        SummonedCard c = null;
        if (field[index] != null) {
            c = field[index];
        }
        return c;
    }

    /**
     * Rotate the Specified Character Position from Attack to Defense and Vice Versa
     * @param index the summoned character array index
     */
    public void changeCardPosition(int index) {
        if (field[index] != null) {
            field[index].rotate();
        }
    }

    /**
     * Determine the position of the specified SummonedCharacter
     * @param index the summoned character array index
     * @return whether the specified card is in attack mode
     */
    public boolean isAttackMode(int index) {
        return field[index].isAttackMode();
    }
    
    /**
     * Attach Skill of specific location into the summoned character
     * @param index the targetted summoned character array index
     * @param skill The skill that want to be attached
     * @param location The Location of the skill
     */
    public void attachSkill(int index, Skill skill,String location){
        field[index].attachSkill(skill,location);
    }

    /**
     * Determine whether the Character Field is Empty
     * @return True if the Character Field is Empty
     */
    public boolean isEmpty() {
        for (int i = 0; i < 6; i++) {
            if (field[i] != null) {
                return false;
            }
        }
        return true;
    }
}
