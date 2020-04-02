package com.avatarduel.model;

public class Character {
    private String name;
    private String description;
    private Element element;
    private int attack;
    private int defense;
    private int power;

    public Character() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
        this.attack = this.defense = this.power = 0;
    }

    public Character(String name, String description, Element element, int attack, int defense, int power) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getPower() { return power; }
}
