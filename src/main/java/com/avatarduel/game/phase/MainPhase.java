package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.model.Player;

public class MainPhase extends Phase {

    public MainPhase(Player playerNow) throws Exception {
        super(playerNow);
        events.addEvent(Event.MAIN_PHASE);
        events.subscribe(Event.MAIN_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        events.notify(Event.MAIN_PHASE, playerNow.getName());
        System.out.println(playerNow.getName() + "MAIN PHASE");
        //algoritma
    }
}
