package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Attribute;
import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;
import com.avatarduel.model.Player;

public class PowerUp extends Skill {
    
    public PowerUp(String name, String description, Element element, int power) {
        super(name, description, element, power);
    }

    @Override
    public void activate(Player destination, int index) {
        boolean isAttackMode = destination.field.getCharacterField().isAttackMode(index);
        if (!isAttackMode) {
            destination.field.getCharacterField().changeCardPosition(index);
        }
    }
}
