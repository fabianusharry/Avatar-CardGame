package com.avatarduel.model.Card;

public abstract class Card {
    
    protected String name;
    protected String description;
    protected Element element;
    protected String imgPath;

    public Card(String name, String description, Element element, String imgPath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.imgPath = imgPath;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }
    public String getImgPath() { return imgPath; }

    public abstract int getAttribute(Attribute  attribute);
    public abstract void setAttribute(Attribute  attribute, int value);
    public abstract void render();
}
