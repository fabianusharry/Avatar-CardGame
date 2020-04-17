package com.avatarduel.game.phase;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.model.Player;

public class BattlePhase extends Phase {
    Player opponent;

    public BattlePhase(Player playerNow, Player opponent) throws Exception {
        super(playerNow);
        this.opponent = opponent;
    }

    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + "BATTLE PHASE");
        initPhase();

    }


    public void initPhase() {
        if (playerNow.equals(controller.getP1())) {
            controller.setStageTextP1("battle");
            controller.getP1HandController().setEnableClick(false);
            controller.getP1HandController().setViewEnabled(false);
            controller.disable(controller.battlePhaseP1, true);
            controller.disable(controller.endPhaseP1, false);
        } else {
            controller.setStageTextP2("battle");
            controller.getP2HandController().setEnableClick(false);
            controller.getP2HandController().setViewEnabled(false);
            controller.disable(controller.battlePhaseP2, true);
            controller.disable(controller.endPhaseP2, false);
        }
    }
}
