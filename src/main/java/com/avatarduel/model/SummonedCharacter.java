package com.avatarduel.model;

import com.avatarduel.model.Card.Attribute;
import com.avatarduel.model.Card.Card;

public class SummonedCharacter implements SummonedCard {
    Card character;
    private boolean isAttackMode;

    public SummonedCharacter(Card character, boolean isAttackMode) {
        this.character = character;
        this.isAttackMode = isAttackMode;
    }

    public boolean isAttackMode() {
        return isAttackMode;
    }

    public void rotate() {
        this.isAttackMode = !this.isAttackMode;
    }

    public int getPositionValue() {
         int value;
         if (this.isAttackMode) {
             value = character.getAttribute(Attribute.ATTACK);
         } else {
             value = character.getAttribute(Attribute.DEFENSE);
         }
         return value;
     }

    public Card getCharacter() {
        return character;
    }

    public void setAttackMode(boolean attackMode) {
        isAttackMode = attackMode;
    }
}
