package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.loader.PowerLoader;
import com.avatarduel.model.Player;

public class DrawPhase extends Phase {

    public DrawPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    @Override
    public void run() throws Exception {
        playerNow.resetPowerNow();
        if (playerNow.equals(controller.getP1())) {
            controller.reload(controller.P1Element, new PowerLoader(playerNow).getPane());
            controller.P1Field.setDisable(true);
            controller.getP1HandController().setEnableClick(false);
            controller.getP1HandController().setDisableLand(false);
            controller.disable(controller.mainPhaseP1, true);
            controller.disable(controller.battlePhaseP1, true);
            controller.disable(controller.endPhaseP1, true);
        } else {
            controller.P2Field.setDisable(true);
            controller.getP2HandController().setEnableClick(false);
            controller.getP2HandController().setDisableLand(false);
            controller.disable(controller.mainPhaseP2, true);
            controller.disable(controller.battlePhaseP2, true);
            controller.disable(controller.endPhaseP2, true);
        }
    }
}
