package com.avatarduel.model.card.effect;

import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.Player;

public class Destroy extends Skill {

    public Destroy(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, power, imgPath);
    }
    
    @Override
    public void activate(Player destination, int index,String location) {
        SummonedCard destinationCard = destination.field.getCharacterField().removeCard(index);
    }
}
