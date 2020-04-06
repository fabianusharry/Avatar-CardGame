package com.avatarduel.model.Field;

public class CardField {
    Field characterField;
    Field skillField;

    public CardField() {
        this.characterField = new CharacterField();
        this.skillField = new SkillField();
    }

    public Field getCharacterField() {
        return characterField;
    }

    public Field getSkillField() {
        return skillField;
    }
}
