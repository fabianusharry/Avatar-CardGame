package com.avatarduel.model.field;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;
import com.avatarduel.model.card.Skill;
import com.avatarduel.util.Constants;

public class CharacterField {
    private SummonedCard[] field;

    public CharacterField() {
        field = new SummonedCharacter[Constants.nFieldInCharacterField];
    }

    public void placeCard(int index, Card card) {
        if (field[index] == null) {
            field[index] = new SummonedCharacter(card, true);
            // habis placeCard invoke player buat milih mau attack mode atau defense mode dari GUI, kalo mau defense -> panggil summonedCharacter.rotate();
        } 
    }

    public SummonedCard removeCard(int index) {
        SummonedCard result = field[index];
        field[index] = null;
        return result;
    }

    public SummonedCard getCard(int index) {
        SummonedCard c = null;
        if (field[index] != null) {
            c = field[index];
        }
        return c;
    }

    public void changeCardPosition(int index) {
        if (field[index] != null) {
            field[index].rotate();
        }
    }

    public boolean isAttackMode(int index) {
        return field[index].isAttackMode();
    }
    
    public void attachSkill(int index, Skill skill,String location){
        field[index].attachSkill(skill,location);
    }

    public boolean isEmpty() {
        for (int i = 0; i < 6; i++) {
            if (field[i] != null) {
                return false;
            }
        }
        return true;
    }
}
