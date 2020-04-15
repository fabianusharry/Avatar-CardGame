package com.avatarduel.model;

import com.avatarduel.model.card.Element;

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

    public Power(Power other) {
        power = new HashMap<>();
        power.put(Element.AIR, other.get(Element.AIR));
        power.put(Element.FIRE, other.get(Element.FIRE));
        power.put(Element.EARTH, other.get(Element.EARTH));
        power.put(Element.WATER, other.get(Element.WATER));
    }

    public int get(Element element) {
        return power.get(element);
    }

    public void add(Element element) {
        power.put(element, power.get(element)+1);
    }

    public void set(Element element, int value) {
        power.put(element, value);
    }
}
