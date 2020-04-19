package com.avatarduel.game.phase;

import com.avatarduel.model.Player;

import java.util.List;

/**
 * BattlePhase of the AvatarDuel game
 * @author Tony Eko Yuwono 13518030
 */
public class BattlePhase extends Phase {
    Player opponent;

    /**
     * Creates a new battle phase for player
     * @param playerNow player that now plays
     * @param opponent opponent of player
     * @throws Exception exception when phase cannot be instantiated
     */
    public BattlePhase(Player playerNow, Player opponent) throws Exception {
        super(playerNow);
        this.opponent = opponent;
    }

    /**
     * Run the battle phase
     * @throws Exception exception when the battle phase cannot run normally
     */
    @Override
    public void run() throws Exception {
        System.out.println(playerNow.getName() + "BATTLE PHASE");
        initPhase();
        List<String> test = controller.getP1FieldController().getDisabledInBattle();
    }

    /**
     * Initialize the battle phase
     */
    public void initPhase() {
        if (playerNow.equals(controller.getP1())) {
            controller.setStageTextP1("battle");
            controller.getP1FieldController().reloadBorder();
            controller.getP1HandController().setEnableClick(false);
            controller.getP1HandController().setViewEnabled(false);
            controller.disable(controller.battlePhaseP1, true);
            controller.disable(controller.endPhaseP1, false);
            controller.getP1FieldController().setEnableClick(true);
            controller.getP2FieldController().setEnableClick(false);
            controller.getP2FieldController().setDelete(false);
            controller.getP1FieldController().setOnClick("selectCard");
           
        } else {
            controller.setStageTextP2("battle");
            controller.getP2FieldController().reloadBorder();
            controller.getP2HandController().setEnableClick(false);
            controller.getP2HandController().setViewEnabled(false);
            controller.disable(controller.battlePhaseP2, true);
            controller.disable(controller.endPhaseP2, false);
            controller.getP2FieldController().setEnableClick(true);
            controller.getP1FieldController().setEnableClick(false);
            controller.getP1FieldController().setDelete(false);
            controller.getP2FieldController().setOnClick("selectCard");
        }
    }
}
