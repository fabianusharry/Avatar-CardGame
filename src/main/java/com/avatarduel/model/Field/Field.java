package com.avatarduel.model.Field;

import com.avatarduel.model.Card.Card;

public interface Field {
    public void placeCard(int index, Card card);
    public void removeCard(int index);
}
