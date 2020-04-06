package com.avatarduel.model.Field;

import com.avatarduel.model.Card.Card;

public interface Field {
    public void placeCard(int index, Card card);
    public Card removeCard(int index);
    public Card getCard(int index);
    public void changeCardPosition(int index);
}
