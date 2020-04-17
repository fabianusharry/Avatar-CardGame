package com.avatarduel.model;

import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.Element;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SummonedCharacterTest {

    @Test
    public void rotate() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);

        boolean attMode = true;
        SummonedCharacter avatar = new SummonedCharacter(card, attMode);
        avatar.rotate();

        assertEquals(!attMode, avatar.isAttackMode());
    }

    @Test
    public void getPositionValue() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);

        SummonedCharacter avatar = new SummonedCharacter(card, true);

        assertEquals(attack, avatar.getPositionValue());
        avatar.rotate();
        assertEquals(defense, avatar.getPositionValue());
    }
}