package com.avatarduel.model;

import com.avatarduel.model.Card.Card;

public interface SummonedCard {

    boolean isAttackMode();
    void rotate();
    int getPositionValue();
    Card getCharacter();
    void setAttackMode(boolean attackMode);

    // menggambar kartu ke layar
//    void render();
}
