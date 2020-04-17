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
        if (playerNow.getHandCards().size() == 11) {
            playerNow.getHandCards().take(10); // buang kartu index terakhir
            if (playerNow.equals(controller.getP1())) {
                controller.disable(controller.endPhaseP1, true);
            } else {
                controller.disable(controller.endPhaseP2, true);
            }
        }
        controller.getManager().changeTurn();
    }

}
