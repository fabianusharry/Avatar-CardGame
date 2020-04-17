package com.avatarduel.model.card;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void getAttribute() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);

        assertEquals(attack, card.getAttribute(Attribute.ATTACK));
        assertEquals(defense, card.getAttribute(Attribute.DEFENSE));
        assertEquals(power, card.getAttribute(Attribute.POWER));
    }

    @Test
    public void setAttribute() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);

        int newAttack = 300;
        int newDefense = 400;

        card.setAttribute(Attribute.ATTACK, newAttack);
        card.setAttribute(Attribute.DEFENSE, newDefense);

        assertEquals(newAttack, card.getAttribute(Attribute.ATTACK));
        assertEquals(newDefense, card.getAttribute(Attribute.DEFENSE));
    }
}