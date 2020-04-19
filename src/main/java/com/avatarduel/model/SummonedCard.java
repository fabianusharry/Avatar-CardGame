package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;

import java.util.List;

/** 
 * 
 * SummonedCard.java
 * Interface class that is implemented for the cards that has been summoned
 * 
 */
public interface SummonedCard {

    /**
     * Determine the position of the card summoned
     * @return whether the card is in attack mode
     */
    boolean isAttackMode();

    /**
     * Determine if the summoned card have power up attached
     * @return whether the card have power up attached
     */
    boolean havePowerUp();

    /**
     * Rotate the Card Position from Attack to Defense and Vice Versa
     */
    void rotate();

    /**
     * Get Value of Current Position of the Card
     * Example :
     * Card is in attack Mode : Get Attack Value
     * Card is in Defense Mode : Get Defense Value
     * @return The Position Value
     */
    int getPositionValue();

    /**
     * Get The Card Class of the summoned Card
     * @return The Card Class of the Summoned Card
     */
    Card getCharacter();

    /**
     * Return All the skills that is currently attached to the summoned card
     * @return list of skill, each element represents a Skill Class
     */
    List<Skill> getSkillAttached();

    /**
     * Remove a specified skill that is attached to the summoned card
     * @param skill the specified skilled that is desired to be removed
     */
    void removeSkillAttached(Skill skill);

    /**
     * Get the Location of all skills attached
     * @return List of string that represents the location of each skill
     */
    List<String> getSkillLocation();

    /**
     * Remove the Specific Location of skill
     * @param location the location of skill that want to be removed
     */
    void removeSkillLocation(String location);

    /**
     * Set the Attack Mode True or False
     * @param attackMode the desired boolean of attack mode
     */
    void setAttackMode(boolean attackMode);

    /**
     * Attach Skill of specific location into the summoned card
     * @param skill The skill that want to be attached
     * @param location The location of the skill
     */
    void attachSkill(Skill skill,String location);
}
