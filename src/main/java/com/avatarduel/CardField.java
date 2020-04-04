package com.avatarduel;

public class CardField {
    CharacterField characterField;
    SkillField skillField;

    public CardField() {
        this.characterField = new CharacterField();
        this.skillField = new SkillField();
    }

    public CharacterField getCharacterField() {
        return characterField;
    }

    public SkillField getSkillField() {
        return skillField;
    }
}
