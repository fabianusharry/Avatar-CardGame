package com.avatarduel.model.Card;

import com.avatarduel.model.Element;

public class Character extends Card {

    protected int attack;
    protected int defense;
    protected int power;

    public Character(String name, String description, Element element, int attack, int defense, int power) {
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
