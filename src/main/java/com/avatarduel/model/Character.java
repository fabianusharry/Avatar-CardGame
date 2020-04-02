package com.avatarduel.model;

public class Character extends Card {

    private int attack;
    private int defense;
    private int power;

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
}
