package com.avatarduel.game;

import com.avatarduel.model.Player;

/**
 * Turn manager for switching player turn
 * @author Tony Eko Yuwono 13518030
 */
public class TurnManager {
    private int counter;
    private Turn turn;
    private Player P1;
    private Player P2;

    /**
     * Initialize the turn with P1 as player now and P2 as opponent
     * @param P1 player now
     * @param P2 player opponent
     */
    public TurnManager(Player P1, Player P2) {
        this.P1 = P1;
        this.P2 = P2;
        this.counter = 1;
    }

    /**
     * Managing turn switching
     * @throws Exception exception when a new tun cannot be instantiated
     */
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

    /**
     * Change turn to another player
     * @throws Exception exception when turn cannot be instantiated
     */
    public void changeTurn() throws Exception {
        this.counter++;
        startTurn();
    }

    /**
     * Get player now turn
     * @return turn
     */
    public Turn getTurn() {
        return turn;
    }
}
