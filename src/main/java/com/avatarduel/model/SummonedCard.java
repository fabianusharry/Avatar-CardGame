package com.avatarduel.model;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;

import java.util.List;

public interface SummonedCard {

    boolean isAttackMode();
    boolean havePowerUp();
    void rotate();
    int getPositionValue();
    Card getCharacter();
    List<Skill> getSkillAttached();
    void removeSkillAttached(Skill skill);
    List<String> getSkillLocation();
    void removeSkillLocation(String location);
    void setAttackMode(boolean attackMode);
    void attachSkill(Skill skill,String location);
}
