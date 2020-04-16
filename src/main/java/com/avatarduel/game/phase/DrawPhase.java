package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.model.Player;

public class DrawPhase extends Phase {

    public DrawPhase(Player playerNow) throws Exception {
        super(playerNow);
        events.addEvent(Event.DRAW_PHASE);
        events.subscribe(Event.DRAW_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        playerNow.resetPowerNow();
        events.notify(Event.DRAW_PHASE, playerNow.getName());
    }
}
