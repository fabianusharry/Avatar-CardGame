package com.avatarduel.model.card.effect;

import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
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
    public void activate(Player destination, int index,String location) {
        Card destinationCard = destination.field.getCharacterField().getCard(index).getCharacter();
        int newAttack = destinationCard.getAttribute(Attribute.ATTACK)+this.attack;
        int newDefense = destinationCard.getAttribute(Attribute.DEFENSE)+this.defense;
        destinationCard.setAttribute(Attribute.ATTACK, newAttack);
        destinationCard.setAttribute(Attribute.DEFENSE, newDefense);
        
        destination.field.getCharacterField().attachSkill(index, this,location);
    }
}
