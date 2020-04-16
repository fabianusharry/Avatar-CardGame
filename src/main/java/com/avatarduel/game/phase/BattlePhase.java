package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.model.Player;

public class BattlePhase extends Phase {
    Player opponent;

    public BattlePhase(Player playerNow, Player opponent) throws Exception {
        super(playerNow);
        this.opponent = opponent;
        events.addEvent(Event.BATTLE_PHASE);
        events.subscribe(Event.BATTLE_PHASE, GameController.getInstance());
    }

    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + "BATTLE PHASE");
        events.notify(Event.BATTLE_PHASE, playerNow.getName());
        //algoritma
    }

}
