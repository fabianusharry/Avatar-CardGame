package com.avatarduel.model.card;

import com.avatarduel.model.Player;

public abstract class Skill extends Card {
   
    protected int power;

    public Skill(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, imgPath);
        this.power = power;
    }

    public String getName() { return name; }
    public int getPower() { return power; }

    public abstract void activate(Player destination, int index);

    @Override
    public int getAttribute(Attribute attribute) {
        int result = -1;
        if (attribute == Attribute.POWER) {
            result = power;
        }
        return  result;
    }

    @Override
    public void setAttribute(Attribute attribute, int value) { } // atribut Skill card tidak perlu ada yang di set
}
