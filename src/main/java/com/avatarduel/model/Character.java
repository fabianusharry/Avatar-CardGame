package com.avatarduel.model;

public class Character extends Card {

    protected int attack;
    protected int defense;
    protected int power;

    public Character() {
        super();
        this.attack = this.defense = this.power = 0;
    }

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
    public int activate(String attr) {
        int result = -1;
        if (attr == "attack") {
            result = getAttack();
        }
        if (attr == "defense") {
            result = getDefense();
        }
        if (attr == "power") {
            result = getPower();
        }
        return result;
    }
}
