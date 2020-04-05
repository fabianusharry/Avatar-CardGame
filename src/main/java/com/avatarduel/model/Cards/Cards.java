package com.avatarduel.model.Cards;

import com.avatarduel.model.Card.Card;
import java.util.List;

public interface Cards {
    public abstract int size();
    public abstract void add(Card newCard);
    public abstract Card take();
}