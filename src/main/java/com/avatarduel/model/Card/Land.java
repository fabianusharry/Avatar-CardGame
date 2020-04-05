package com.avatarduel.model.Card;

import com.avatarduel.model.Element;

public class Land extends Card {

    public Land(String name, String description, Element element) {
        super(name, description, element);
    }

    @Override
    public void use() { }
}