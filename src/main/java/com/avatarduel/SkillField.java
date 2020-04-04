package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.Skill;

public class SkillField implements Field {
    Skill[] field;

    public SkillField() {
        field = new Skill[8];
    }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = (Skill) card;
        } // else throw exception (?)
    }

    public void removeCard(int index) {
        field[index] = null;
    }
}