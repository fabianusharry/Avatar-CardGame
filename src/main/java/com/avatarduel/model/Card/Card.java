package com.avatarduel.model.Card;

public abstract class Card {
    
    protected String name;
    protected String description;
    protected Element element;

    public Card(String name, String description, Element element) {
        this.name = name;
        this.description = description;
        this.element = element;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }

    public abstract int getAttribute(Attribute  attribute);
    public abstract void setAttribute(Attribute  attribute, int value);
}
