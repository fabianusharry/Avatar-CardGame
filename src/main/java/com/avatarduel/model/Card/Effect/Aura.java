package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;

public class Aura extends Skill {
    
    private int attack;
    private  int defense;

    public Aura(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element, power);
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }


    @Override
    public void use() { }
}
