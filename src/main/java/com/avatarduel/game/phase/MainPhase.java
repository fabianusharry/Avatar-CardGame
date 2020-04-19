package com.avatarduel.game.phase;

import com.avatarduel.model.Player;

/**
 * MainPhase of the AvatarDuel game
 * @author Tony Eko Yuwono 13518030
 */
public class MainPhase extends Phase {

    /**
     * Creates a new main phase for player
     * @param playerNow player that now plays
     * @throws Exception exception when phase cannot be instantiated
     */
    public MainPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    /**
     * Run the end phase
     * @throws Exception exception when the end phase cannot run normally
     */
    @Override
    public void run() throws Exception {
        if (playerNow.equals(controller.getP1())) {
            controller.setStageTextP1("main");
            controller.P1deck.setDisable(true);
            controller.P1Field.setDisable(false);
            controller.getP1HandController().setEnableClick(true);
            controller.disable(controller.mainPhaseP1, true);
            controller.disable(controller.battlePhaseP1, false);
            controller.getP1FieldController().setEnableClick(true);
            controller.getP1FieldController().setOnClick("modify");
        } else {
            controller.setStageTextP2("main");
            controller.P2deck.setDisable(true);
            controller.P2Field.setDisable(false);
            controller.getP2HandController().setEnableClick(true);
            controller.disable(controller.mainPhaseP2, true);
            controller.disable(controller.battlePhaseP2, false);
            controller.getP2FieldController().setEnableClick(true);
            controller.getP2FieldController().setOnClick("modify");
        }
    }
}
