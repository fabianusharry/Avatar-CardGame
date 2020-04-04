package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.SummonedCharacter;

public class CharacterField implements Field {
    SummonedCharacter[] field;

    public CharacterField() {
        field = new SummonedCharacter[8];
    }
    public SummonedCharacter[] getField() { return field; }

    public void placeCard(int index, Card card, boolean isAttackMode) {
        placeCard(index, card);
        field[index].setAttackMode(isAttackMode);
    }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = new SummonedCharacter(card, true);
        } // else throw exception (?)
        else {
            System.out.println("UDAH DIISI " + field[index].getCharacter().getName());
        }
    }

    public void removeCard(int index) {
        field[index] = null;
    }
}
