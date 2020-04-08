package com.avatarduel.model.card.effect;

import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.Player;

public class PowerUp extends Skill {
    
    public PowerUp(String name, String description, Element element, int power, String imgPath) {
        super(name, description, element, power, imgPath);
    }

    @Override
    public void activate(Player destination, int index) {
        boolean isAttackMode = destination.field.getCharacterField().isAttackMode(index);
        if (!isAttackMode) {
            destination.field.getCharacterField().changeCardPosition(index);
        }
    }
}
