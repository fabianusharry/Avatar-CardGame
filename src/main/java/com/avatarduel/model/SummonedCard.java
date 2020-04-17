package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;

import java.util.List;

public interface SummonedCard {

    boolean isAttackMode();
    void rotate();
    int getPositionValue();
    Card getCharacter();
    List<Skill> getSkillAttached();
    List<String> getSkillLocation();
    void setAttackMode(boolean attackMode);
    void attachSkill(Skill skill,String location);
}
