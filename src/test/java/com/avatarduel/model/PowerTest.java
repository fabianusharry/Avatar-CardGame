package com.avatarduel.model;

import com.avatarduel.model.card.Element;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerTest {

    @Test
    public void add() {
        Power pow = new Power();
        pow.add(Element.FIRE);
        pow.add(Element.AIR);
        pow.add(Element.AIR);
        assertEquals(pow.get(Element.EARTH), 0);
        assertEquals(pow.get(Element.FIRE), 1);
        assertEquals(pow.get(Element.AIR), 2);
    }

    @Test
    public void set() {
        Power pow = new Power();
        pow.add(Element.FIRE);
        pow.add(Element.AIR);
        pow.add(Element.AIR);

        pow.set(Element.FIRE, 5);
        assertEquals(pow.get(Element.FIRE), 5);
    }
}