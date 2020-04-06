package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;

public class PowerUp extends Skill {
    
    public PowerUp(String name, String description, Element element, int power) {
        super(name, description, element, power);
    }

    @Override
    public void use(){ }
}
