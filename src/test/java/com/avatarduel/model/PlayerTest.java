package com.avatarduel.model;

import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Element;
import com.avatarduel.util.Constants;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void setHP() throws Exception {
        Player P = new Player("Player");

        // test simple set hp
        int newHP = 60;
        P.setHP(newHP);
        assertEquals(P.getHP(), newHP);
    }

    @Test
    public void setHpBelowZero() throws Exception {
        Player P = new Player("Player");
        int newHP = -20;
        P.setHP(newHP);

        // player hp should remain zero
        assertEquals(P.getHP(), 0);
    }

    @Test
    public void draw() throws Exception {
        Player P = new Player("Player");

        // initial handcard should be 7
        P.draw();
        // handcard should now be 8
        assertEquals(P.getHandCards().size(), 8);
    }

    @Test
    public void drawToMax() throws Exception {
        Player P = new Player("Player");

        // max card on hand value stored in constant maxCardOnHand
        while (P.getHandCards().size() < Constants.maxCardOnHand) {
            P.draw();
        }

        // draw once more
        P.draw();

        // handcard should remain maxCardOnHand
        assertEquals(P.getHandCards().size(), Constants.maxCardOnHand);
    }

    @Test
    public void takeCard() throws  Exception {
        Player P = new Player("Player");
        int prevSize = P.getHandCards().size();
        Card card = P.takeCard(2);
        assertEquals(P.getHandCards().size(), prevSize-1);
    }

    @Test
    public void takeLandCard() throws Exception {
        Player P = new Player("Player");

        // take and draw until we get land card
        Card card = null;
        while (!(card instanceof com.avatarduel.model.card.Land)) {
            card = P.takeCard(0);
            if (P.getHandCards().size() == 0) {
                P.draw();
            }
        }

        // max power same with card element should increase one
        assertEquals(P.getPowerNow().get(card.getElement()), 1);
    }

    @Test
    public void takeSkillOrCharacterCardPowerEnough() throws Exception {
        Player P = new Player("Player");

        // increase player's power
        int power = 10;
        P.getPowerNow().set(Element.AIR, power);
        P.getPowerNow().set(Element.FIRE, power);
        P.getPowerNow().set(Element.WATER, power);
        P.getPowerNow().set(Element.EARTH, power);

        // take and draw until we get skill or character card
        Card card = null;
        while (P.getHandCards().peek(0) instanceof com.avatarduel.model.card.Land) {
            if (P.getHandCards().size() == 0) {
                P.draw();
            }
        }
        card = P.takeCard(0);

        // because each element power is high card should
        // success to be taken
        assertNotNull(card);
        assertEquals(P.getPowerNow().get(card.getElement()), power-card.getAttribute(Attribute.POWER));
    }

    @Test
    public void takeSkillOrCharacterCardPowerNotEnough() throws Exception {
        Player P = new Player("Player");

        // take and draw until we get skill or character card
        Card card = null;
        while (true) {

            // player's power
            P.getPowerNow().set(Element.AIR, 0);
            P.getPowerNow().set(Element.FIRE, 0);
            P.getPowerNow().set(Element.WATER, 0);
            P.getPowerNow().set(Element.EARTH, 0);

            if (!(P.getHandCards().peek(0) instanceof com.avatarduel.model.card.Land)) {
                card = P.takeCard(0);
                break;
            } else {
                card = P.takeCard(0);
                card = null;
            }
            if (P.getHandCards().size() == 0) {
                P.draw();
            }
        }

        // because element power is always 0 card should
        // failed to be taken
        assertNull(card);
    }

    @Test
    public void resetPowerNow() throws Exception {
        Player P = new Player("Player");
        P.getMaxPower().add(Element.AIR);
        P.getMaxPower().add(Element.AIR);

        P.resetPowerNow();
        // Air element should be 2
        assertEquals(P.getPowerNow().get(Element.AIR), 2);
    }
}