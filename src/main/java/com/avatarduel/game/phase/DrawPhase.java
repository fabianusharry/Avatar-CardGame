package com.avatarduel.game.phase;

import com.avatarduel.exceptions.EndGameException;
import com.avatarduel.view.loader.GameLoader;
import com.avatarduel.view.loader.MessageBoxLoader;
import com.avatarduel.view.loader.PowerLoader;
import com.avatarduel.model.Player;

public class DrawPhase extends Phase {

    public DrawPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    @Override
    public void run() throws Exception {
        playerNow.resetPowerNow();
        if (playerNow.getDeck().size() == 0) {
            new MessageBoxLoader(new EndGameException(playerNow.getName() + " Kehabisan Kartu")).render();
            GameLoader.getInstance().exit();
        }
        if (playerNow.equals(controller.getP1())) {
            controller.reload(controller.P1Element, new PowerLoader(playerNow).getPane());
            controller.getP1HandController().setEnableClick(false);
            controller.getP1HandController().setDisableLand(false);
            controller.disable(controller.mainPhaseP1, true);
            controller.disable(controller.battlePhaseP1, true);
            controller.disable(controller.endPhaseP1, true);
            controller.getP1FieldController().clearDisabledInBattle();
        } else {
            controller.reload(controller.P2Element, new PowerLoader(playerNow).getPane());
            controller.getP2HandController().setEnableClick(false);
            controller.getP2HandController().setDisableLand(false);
            controller.disable(controller.mainPhaseP2, true);
            controller.disable(controller.battlePhaseP2, true);
            controller.disable(controller.endPhaseP2, true);
            controller.getP2FieldController().clearDisabledInBattle();
        }
        controller.getP1FieldController().setOnClick("");
        controller.getP2FieldController().setOnClick("");
    }
}
