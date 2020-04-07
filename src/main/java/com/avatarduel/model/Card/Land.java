package com.avatarduel.model.Card;

public class Land extends Card {

    public Land(String name, String description, Element element) {
        super(name, description, element);
    }

    @Override
    public int getAttribute(Attribute attribute) { return -1; }

    @Override
    public void setAttribute(Attribute attribute, int value) { } // attribut land card tidak ada yang perlu di set
}