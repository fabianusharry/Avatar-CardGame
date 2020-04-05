package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Element;

public class Aura extends Skill {
    
    private int attack;
    private  int defense;
    private int power;

    public Aura(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getPower() { return power; }

    @Override
    public void use() { }
}
