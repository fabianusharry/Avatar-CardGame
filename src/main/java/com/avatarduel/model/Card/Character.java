package com.avatarduel.model.Card;

public class Character extends Card {

    protected int attack;
    protected int defense;
    protected int power;

    public Character(String name, String description, Element element, int attack, int defense, int power, String imgPath) {
        super(name, description, element, imgPath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    @Override
    public int getAttribute(Attribute attribute) {
        int result = -1;
        if (attribute == Attribute.ATTACK) {
            result = attack;
        } else if (attribute == Attribute.DEFENSE) {
            result = defense;
        } else if (attribute == Attribute.POWER) {
            result = power;
        }
        return result;
    }

    @Override
    public void setAttribute(Attribute  attribute, int value) {
        if (attribute == Attribute.ATTACK) {
            attack = value;
        } else if (attribute == Attribute.DEFENSE) {
            defense = value;
        }
    }

    @Override
    public void render() {

    }
}
