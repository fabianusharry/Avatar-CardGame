package com.avatarduel.model.Card.Effect;

import com.avatarduel.model.Card.Skill;
import com.avatarduel.model.Card.Element;

public class PowerUp extends Skill {
    
    public PowerUp(String name, String description, Element element) {
        super(name, description, element);
    }

    @Override
    public void use(){ }
}
