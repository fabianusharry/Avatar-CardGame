package com.avatarduel.model;

public class Card {
    protected String name;
    protected String description;
    protected Element element;

    public Card() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    public Card(String name, String description, Element element) {
        this.name = name;
        this.description = description;
        this.element = element;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }
}
