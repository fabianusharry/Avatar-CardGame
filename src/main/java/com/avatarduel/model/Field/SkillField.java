package com.avatarduel.model.Field;

import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Card.Skill;

public class SkillField {
    Skill[] field;

    public SkillField() {
        field = new Skill[8];
    }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = (Skill) card;
        } // else throw exception (?)
    }

    public Card removeCard(int index) {
        Card result = field[index];
        field[index] = null;
        return result;
    }

    public Card getCard(int index) {
        return field[index];
    }
}