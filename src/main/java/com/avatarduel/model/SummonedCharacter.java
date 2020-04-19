package com.avatarduel.model;

import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * SummonedCharacter.java
 * A Class to Implement A Summoned Character that is Summoned into the field
 * ATTRIBUTES : 
 * Character            : The Character (constructed in the Character Class) that is summoned into the field
 * isAttackMode Boolean : The Position of the Summoned Character (Attack or Defense)
 * skillAttached List   : List of the Skills Attached to the Character
 * skillLocation List   : The Location of Attached Skills
 */
public class SummonedCharacter implements SummonedCard {
    Card character;
    private boolean isAttackMode;
    private List<Skill> skillAttached;
    private List<String> skillLocation;

    /**
     * Creates a new Summoned Character Object
     * @param character the specific character taken from the Character Class
     * @param isAttackMode the initial position of the character
     */
    public SummonedCharacter(Card character, boolean isAttackMode) {
        this.character = character;
        this.isAttackMode = isAttackMode;
        skillAttached = new ArrayList<>();
        skillLocation = new ArrayList<>();
    }

    /**
     * Determine the position of the character summoned
     * @return whether the card is in attack mode
     */
    public boolean isAttackMode() {
        return isAttackMode;
    }
    
    /**
     * Determine if the summoned character have power up attached
     * @return whether the character have power up attached
     */
    public boolean havePowerUp(){
        for(Skill s: skillAttached){
            if(s instanceof com.avatarduel.model.card.effect.PowerUp){
                return true;
            }
        }
        return false;
    }

    /**
     * Rotate the Character Position from Attack to Defense and Vice Versa
     */
    public void rotate() {
        this.isAttackMode = !this.isAttackMode;
    }

     /**
     * Get Value of Current Position of the Character
     * Example :
     * Card is in attack Mode : Get Attack Value
     * Card is in Defense Mode : Get Defense Value
     * @return The Position Value
     */
    public int getPositionValue() {
         int value;
         if (this.isAttackMode) {
             value = character.getAttribute(Attribute.ATTACK);
         } else {
             value = character.getAttribute(Attribute.DEFENSE);
         }

         return value;
     }

    /**
     * Get The Card Class of the summoned Card
     * @return The Card Class of the Summoned Card
     */
    public Card getCharacter() {
        return character;
    }

    /**
     * Set the Attack Mode True or False
     * @param attackMode the desired boolean of attack mode
     */
    public void setAttackMode(boolean attackMode) {
        isAttackMode = attackMode;
    }
 
     /**
     * Attach Skill of specific location into the summoned character
     * @param skill The skill that want to be attached
     * @param location The location of the skill
     */
    public void attachSkill(Skill skill,String location){
        skillAttached.add(skill);
        skillLocation.add(location);
    }
 
     /**
     * Return All the skills that is currently attached to the summoned card
     * @return list of skill, each element represents a Skill Class
     */
    public List<Skill> getSkillAttached() { return skillAttached; }
 
    /**
     * Remove a specified skill that is attached to the summoned card
     * @param skill the specified skilled that is desired to be removed
     */
    public void removeSkillAttached(Skill skill){
        skillAttached.remove(skill);
    }

    /**
     * Get the Location of all skills attached
     * @return List of string that represents the location of each skill
     */
    public List<String> getSkillLocation() { return skillLocation; }
    
    /**
     * Remove the Specific Location of skill
     * @param location the location of skill that want to be removed
     */
    public void removeSkillLocation(String location){
        skillLocation.remove(location);
    }
}
