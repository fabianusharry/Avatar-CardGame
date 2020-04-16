package com.avatarduel.game;

import com.avatarduel.model.Player;

public class TurnManager {
    private int counter;
    private Turn turn;
    private Player P1;
    private Player P2;
    Phase phaseNow;

    public TurnManager(Player P1, Player P2) {
        this.P1 = P1;
        this.P2 = P2;
        this.counter = 1;
    }

    public void startTurn() throws Exception {
        if (counter%2 == 1) {
            turn = new Turn(P1, P2);
            System.out.println("Draw phase P1");
        } else {
            turn = new Turn(P2, P1);
            System.out.println("Draw phase P2");
        }
        turn.startPhase();
    }

    public void endTurn(){
        this.counter++;
    }

    public Turn getTurn() {
        return turn;
    }
}
