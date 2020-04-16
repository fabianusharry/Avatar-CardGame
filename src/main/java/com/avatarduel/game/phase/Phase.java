package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public abstract class Phase {
    protected Player playerNow;
    protected EventManager events;

    public Phase(Player playerNow) throws Exception {
        this.playerNow = playerNow;
        events = new EventManager(Event.NEXT_PHASE);
        events.subscribe(Event.NEXT_PHASE, GameController.getInstance());
    }

    public void next() throws Exception {
        events.notify(Event.NEXT_PHASE, null);
    }

    abstract public void run() throws Exception;

}
