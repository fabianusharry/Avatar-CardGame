package com.avatarduel.model.Card;

public abstract class Skill extends Card {
   
    protected int power;

    public Skill(String name, String description, Element element) {
        super(name, description, element);
    }

    public abstract void use();
}
