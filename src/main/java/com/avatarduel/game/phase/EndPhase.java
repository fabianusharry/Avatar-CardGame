package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.model.Player;

public class EndPhase extends Phase {

    public EndPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + " END PHASE");
        controller.getManager().changeTurn();
    }

}
