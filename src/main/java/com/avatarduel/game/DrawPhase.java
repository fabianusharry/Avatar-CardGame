package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class DrawPhase implements Phase {
    private Player player;
    private EventManager events;

    DrawPhase(Player player, GameController controller) {
        this.player = player;
//        events = new EventManager(Event.DISABLEPLAYERHANDS, Event.);
    }

    @Override
    public void run() {
        player.resetPowerNow();

    }
}
