package com.avatarduel.model.field;

import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.Element;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterFieldTest {

    @Test
    public void placeCard() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card1 = new Character(name, desc, el, attack, defense, power, ImgPath);
        Character card2 = new Character(name, desc, el, attack, defense, power, ImgPath);
        CharacterField arena = new CharacterField();

        // test placing card on empty field
        arena.placeCard(2, card1);
        assertNotNull(arena.getCard(2));

        // test placing card on non-empty field
        arena.placeCard(2, card2);
        assertEquals(arena.getCard(2), card1);
    }

    @Test
    public void removeCard() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);
        CharacterField arena = new CharacterField();
        arena.placeCard(2, card);

        // test removing card
        arena.removeCard(2);
        assertNull(arena.getCard(2));
    }

    @Test
    public void changeCardPosition() {
        String name = "Card name";
        String desc = "Card description";
        Element el = Element.AIR;
        int attack = 100;
        int defense = 200;
        int power = 3;
        String ImgPath = "image path";

        Character card = new Character(name, desc, el, attack, defense, power, ImgPath);
        CharacterField arena = new CharacterField();
        arena.placeCard(2, card);

        // test change card position
        arena.changeCardPosition(2);
        // default position is attack
        assertEquals(arena.isAttackMode(2), false);
    }
}