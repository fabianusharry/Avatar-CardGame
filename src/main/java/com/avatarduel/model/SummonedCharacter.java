package com.avatarduel.model;

import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import java.util.ArrayList;
import java.util.List;

public class SummonedCharacter implements SummonedCard {
    Card character;
    private boolean isAttackMode;
    private List<Skill> skillAttached;
    private List<String> skillLocation;

    public SummonedCharacter(Card character, boolean isAttackMode) {
        this.character = character;
        this.isAttackMode = isAttackMode;
        skillAttached = new ArrayList<>();
        skillLocation = new ArrayList<>();
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
    
    public void attachSkill(Skill skill){
        skillAttached.add(skill);
    }
}
