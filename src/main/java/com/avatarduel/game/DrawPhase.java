package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class DrawPhase implements Phase {
    private Player player;
    private EventManager events;

    DrawPhase(Player player) throws Exception {
        this.player = player;
        events = new EventManager(Event.DRAW_PHASE, Event.NEXT_PHASE);
        events.subscribe(Event.DRAW_PHASE, GameController.getInstance());
        events.subscribe(Event.NEXT_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        player.resetPowerNow();
        events.notify(Event.DRAW_PHASE, player.getName());
    }

    @Override
    public void next() throws Exception {
        events.notify(Event.NEXT_PHASE, "main");
    }


}
