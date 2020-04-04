package com.avatarduel;

import com.avatarduel.model.Card;
import com.avatarduel.model.Skill;

public interface Field {
    public void placeCard(int index, Card card);
    public void removeCard(int index);
}
