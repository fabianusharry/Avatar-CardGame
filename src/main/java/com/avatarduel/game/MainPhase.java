package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class MainPhase implements Phase {
    Player player;
    private EventManager events;

    public MainPhase(Player player) throws Exception {
        this.player = player;
        events = new EventManager(Event.MAIN_PHASE, Event.NEXT_PHASE);
        events.subscribe(Event.NEXT_PHASE, GameController.getInstance());
        events.subscribe(Event.MAIN_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        events.notify(Event.MAIN_PHASE, player.getName());
        System.out.println(player.getName() + "MAIN PHASE");
        //algoritma
    }

    @Override
    public void next() throws Exception {
        events.notify(Event.NEXT_PHASE, "battle");
    }
}
