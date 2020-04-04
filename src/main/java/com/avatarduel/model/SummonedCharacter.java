package com.avatarduel.model;

import com.avatarduel.model.Character;

public class SummonedCharacter {
    Card character;
    private boolean isAttackMode;

    public SummonedCharacter(Card character, boolean isAttackMode) {
        this.character = character;
        this.isAttackMode = isAttackMode;
    }

    public void rotate() {
        this.isAttackMode = !this.isAttackMode;
    }

    public int getPositionValue() {
        int value;
        if (this.isAttackMode == true) {
            value = character.activate("attack");
        } else {
            value = character.activate("defense");
        }
        return value;
    }
}
