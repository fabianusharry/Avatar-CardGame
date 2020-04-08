package com.avatarduel.model.field;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;

public class CharacterField {
    private SummonedCard[] field;

    public CharacterField() {
        field = new SummonedCharacter[8];
    }
    public SummonedCard[] getField() { return field; }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = new SummonedCharacter(card, true);
            // habis placeCard invoke player buat milih mau attack mode atau defense mode dari GUI, kalo mau defense -> panggil summonedCharacter.rotate();
        } // else throw exception (?)
    }

    public Card removeCard(int index) {
        SummonedCard result = field[index];
        field[index] = null;
        return result.getCharacter();
    }

    public Card getCard(int index) {
        return field[index].getCharacter();
    }

    public void changeCardPosition(int index) {
        field[index].rotate();
    }

    public boolean isAttackMode(int index) {
        return field[index].isAttackMode();
    }
}
