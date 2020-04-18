package com.avatarduel.game.phase;

import com.avatarduel.model.Player;

public class MainPhase extends Phase {

    public MainPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + "MAIN PHASE");
        if (playerNow.equals(controller.getP1())) {
            System.out.println("MASUUUUUUUUUUUUUUUUUUUK");
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
