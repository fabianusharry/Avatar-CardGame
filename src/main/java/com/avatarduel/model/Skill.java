package com.avatarduel.model;

public abstract class Skill extends Card {
    protected int power;

    public Skill() {
        super();
    }

    public Skill(String name, String description, Element element) {
        super(name, description, element);
    }

    public int getPower() { return power; }

    abstract String effect(); //masih bingung

}
