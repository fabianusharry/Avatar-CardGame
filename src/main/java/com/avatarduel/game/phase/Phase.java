package com.avatarduel.game.phase;

import com.avatarduel.view.controller.GameController;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventManager;
import com.avatarduel.model.Player;

/**
 * Abstract class for phase handling
 * @author Tony Eko Yuwono 13518030
 */
public abstract class Phase {
    protected Player playerNow;
    protected GameController controller;

    /**
     * Phase for playerNow
     * @param playerNow player that now plays
     * @throws Exception exception when a phase cannot be instantiated
     */
    public Phase(Player playerNow) throws Exception {
        this.playerNow = playerNow;
        this.controller = GameController.getInstance();
    }

    /**
     * Run the phase
     * @throws Exception exception when the phase cannot run normally
     */
    abstract public void run() throws Exception;

}
