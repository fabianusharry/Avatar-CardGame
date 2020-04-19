package com.avatarduel.model;

import com.avatarduel.model.card.Element;

import java.util.HashMap;
import java.util.Map;

/** 
 * Power.java
 * A Class that arrange All Power Requirements of the Game
 * ATTRIBUTES:
 * power Map : Represents the Power of Every Element in the Game
 */
public class Power {
    Map<Element, Integer> power;

    /**
     * Construct a New Power Object with default (10) value of power
     */
    public Power() {
        power = new HashMap<>();
        power.put(Element.AIR, 10);
        power.put(Element.FIRE, 10);
        power.put(Element.EARTH, 10);
        power.put(Element.WATER, 10);
        power.put(Element.ENERGY, 10);
    }

    /**
     * Construct a New Power Object where the value of each elements is equal to the value in other Power Object
     */
    public Power(Power other) {
        power = new HashMap<>();
        power.put(Element.AIR, other.get(Element.AIR));
        power.put(Element.FIRE, other.get(Element.FIRE));
        power.put(Element.EARTH, other.get(Element.EARTH));
        power.put(Element.WATER, other.get(Element.WATER));
        power.put(Element.ENERGY, other.get(Element.ENERGY));
    }

    /**
     * Get The Value of Power of specific element
     * @param element the element specified by user
     * @return Value of power of the element
     */
    public int get(Element element) {
        return power.get(element);
    }

    /**
     * Add the current value of Power of specific element by 1
     * @param element the element specified by user
     */
    public void add(Element element) {
        power.put(element, power.get(element)+1);
    }

    /**
     * Set value of power of specific element to value parameter
     * @param element the element specified by user
     * @param value the power after-value desired by user
     */
    public void set(Element element, int value) {
        power.put(element, value);
    }
}
