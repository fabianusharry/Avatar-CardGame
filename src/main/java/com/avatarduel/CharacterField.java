package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.SummonedCharacter;

public class CharacterField implements Field {
    SummonedCharacter[] field;

    public CharacterField() {
        field = new SummonedCharacter[8];
    }

    public void placeCard(int index, Card card, boolean isAttackMode) {
        if (field[index] == null) {
            field[index] = new SummonedCharacter(card, isAttackMode);
        } // else throw exception (?)
        else {
            System.out.println("UDAH DIISI");
        }
    }

    public void removeCard(int index) {
        field[index] = null;
    }
}
