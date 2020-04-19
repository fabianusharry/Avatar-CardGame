package com.avatarduel.model.field;

/**
 * 
 * CardField.java
 * A Class that Build the Card Field Area for a player
 * ATTRIBUTES :
 * characterField   : The Field Area Consists of Character that a player have
 * skillField       : The Field Area Consists of Skills that a player use
 * 
 */
public class CardField {
    CharacterField characterField;
    SkillField skillField;

    /**
     * Creates a new Card Field Object
     */
    public CardField() {
        this.characterField = new CharacterField();
        this.skillField = new SkillField();
    }

    /**
     * Get The Player Current Character Field
     * @return Player's Character Field
     */
    public CharacterField getCharacterField() {
        return characterField;
    }

    /**
     * Get The Player Current Skill Field
     * @return Player's Skill Field
     */
    public SkillField getSkillField() {
        return skillField;
    }
}
