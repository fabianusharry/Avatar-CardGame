package com.avatarduel.model;

public abstract class Skill {
    protected String name;
    protected String description;
    protected Element element;
    protected int power;

    public Skill() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    public Skill(String name, String description, Element element) {
        this.name = name;
        this.description = description;
        this.element = element;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }
    public int getPower() { return power; }

    abstract String effect(); //masih bingung
}
