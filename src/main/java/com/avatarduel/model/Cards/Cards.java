package com.avatarduel.model.Cards;

// import com.avatarduel.CardPack;
import com.avatarduel.model.Card.Card;
import java.util.List;
// import java.util.ArrayList;

public abstract class Cards
{
    protected List<Card> cards;

    public Cards(List<Card> cards)
    {
        this.cards = cards;
    }
    
    public int size() { return this.cards.size(); }
    
    // public abstract void add(Card newCard);
    // public abstract Card Draw();
}