package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;

public interface SummonedCard {

    boolean isAttackMode();
    void rotate();
    int getPositionValue();
    Card getCharacter();
    void setAttackMode(boolean attackMode);
    void attachSkill(Skill skill);
}
