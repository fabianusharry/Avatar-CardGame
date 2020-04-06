package com.avatarduel.model.Card;

public abstract class Skill extends Card {
   
    protected int power;

    public Skill(String name, String description, Element element, int power) {
        super(name, description, element);
        this.power = power;
    }

    public int getPower() { return power; }

    public abstract void use();
}
