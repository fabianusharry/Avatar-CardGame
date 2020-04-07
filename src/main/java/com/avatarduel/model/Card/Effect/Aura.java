package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Attribute;
import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;
import com.avatarduel.model.Player;

public class Aura extends Skill {
    
    private int attack;
    private  int defense;

    public Aura(String name, String description, Element element, int attack, int defense, int power, String imgPath) {
        super(name, description, element, power, imgPath);
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }


    @Override
    public void activate(Player destination, int index) {
        Card destinationCard = destination.field.getCharacterField().getCard(index);
        int newAttack = destinationCard.getAttribute(Attribute.ATTACK)+this.attack;
        int newDefense = destinationCard.getAttribute(Attribute.DEFENSE)+this.defense;
        destinationCard.setAttribute(Attribute.ATTACK, newAttack);
        destinationCard.setAttribute(Attribute.DEFENSE, newDefense);
    }
}
