package com.avatarduel.model;

import com.avatarduel.model.Card.Element;

import java.util.HashMap;
import java.util.Map;

public class Power {
    Map<Element, Integer> power;

    public Power() {
        power = new HashMap<>();
        power.put(Element.AIR, 0);
        power.put(Element.FIRE, 0);
        power.put(Element.EARTH, 0);
        power.put(Element.WATER, 0);
    }

    public int get(Element element) {
        return power.get(element);
    }

    public void add(Element element) {
        power.put(element, power.get(element)+1);
    }
}
