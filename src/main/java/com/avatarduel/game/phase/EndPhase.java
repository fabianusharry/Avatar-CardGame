package com.avatarduel.game.phase;

import com.avatarduel.model.Player;

/**
 * EndPhase of the AvatarDuel game
 * @author Tony Eko Yuwono 13518030
 */
public class EndPhase extends Phase {

    /**
     * Creates a new end phase for player
     * @param playerNow player that now plays
     * @throws Exception exception when phase cannot be instantiated
     */
    public EndPhase(Player playerNow) throws Exception {
        super(playerNow);
    }

    /**
     * Run the end phase
     * @throws Exception exception when the end phase cannot run normally
     */
    @Override
    public void run() throws Exception {
        controller.getP1FieldController().reloadBorder();
        controller.getP2FieldController().reloadBorder();
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
