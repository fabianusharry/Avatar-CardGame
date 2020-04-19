package com.avatarduel.model.field;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.util.Constants;

public class SkillField {
    Skill[] field;

    public SkillField() {
        field = new Skill[Constants.nFieldInSkillField];
    }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = (Skill) card;
        }
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