package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class BattlePhase implements Phase {
    Player playerNow;
    Player opponent;
    private EventManager events;

    public BattlePhase(Player playerNow, Player opponent) throws Exception {
        this.playerNow = playerNow;
        this.opponent = opponent;
        events = new EventManager(Event.BATTLE_PHASE, Event.NEXT_PHASE);
        events.subscribe(Event.NEXT_PHASE, GameController.getInstance());
        events.subscribe(Event.BATTLE_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + "BATTLE PHASE");
        events.notify(Event.BATTLE_PHASE, playerNow.getName());
        //algoritma
    }

    @Override
    public void next() throws Exception {
        events.notify(Event.NEXT_PHASE, "end");
    }
}
