package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Attribute;
import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;
import com.avatarduel.model.Player;

public class Destroy extends Skill {

    public Destroy(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, power, imgPath);
    }
    
    @Override
    public void activate(Player destination, int index) {
        Card destinationCard = destination.field.getCharacterField().removeCard(index);
    }
}
