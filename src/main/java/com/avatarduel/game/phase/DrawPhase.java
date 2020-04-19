package com.avatarduel.game.phase;

import com.avatarduel.exceptions.EndGameException;
import com.avatarduel.view.loader.GameLoader;
import com.avatarduel.view.loader.MessageBoxLoader;
import com.avatarduel.view.loader.PowerLoader;
import com.avatarduel.model.Player;

public class DrawPhase extends Phase {

    /**
     * Creates a new draw phase for player
     * @param playerNow player that now plays
     * @throws Exception exception when phase cannot be instantiated
     */
    public DrawPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    /**
     * Run the draw phase
     * @throws Exception exception when the draw phase cannot run normally
     */
    @Override
    public void run() throws Exception {
        playerNow.resetPowerNow();
        try {
            if (playerNow.getDeck().size() == 0) {
                throw new EndGameException(playerNow.getName() + " Kehabisan Kartu");
            }
        } catch (Exception e) {
            new MessageBoxLoader(e).render();
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
