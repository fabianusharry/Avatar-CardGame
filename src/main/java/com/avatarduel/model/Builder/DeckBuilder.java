package com.avatarduel.model.Builder;

import java.util.Collections;
import com.avatarduel.CardPack;
import com.avatarduel.model.Cards.Deck;

public class DeckBuilder extends Deck
{
    public DeckBuilder(CardPack c)
    {
        super(c);
    }

    public DeckBuilder shuffle()
    {
        Collections.shuffle(this.cards);
        return this;
    }

    public Deck Build()
    {   
        return this;
    }
}