package com.avatarduel.model.card;

import com.avatarduel.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillTest {

    @Test
    public void getAttribute() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 100;
        int power = 3;
        String ImgPath = "image path";

        Skill card = new Skill(name, desc, el, power, ImgPath) {
        
        @Override
        public void activate(Player destination, int index) { }
        };

        assertEquals(power, card.getAttribute(Attribute.POWER));
    }
}