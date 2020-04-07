package com.avatarduel.model.Card;

import com.avatarduel.model.Player;

public abstract class Skill extends Card {
   
    protected int power;

    public Skill(String name, String description, Element element, int power) {
        super(name, description, element);
        this.power = power;
    }

    public int getPower() { return power; }

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

    public abstract void activate(Player destination, int index);
}
