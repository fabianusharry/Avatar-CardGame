package com.avatarduel.game;

import com.avatarduel.game.phase.*;
import com.avatarduel.view.controller.GameController;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventManager;
import com.avatarduel.model.Player;

/**
 * Turn of player in the AvatarDuel game
 * @author Tony Eko Yuwono 13518030
 */
public class Turn {
    Player playerNow;
    Player playerOpponent;
    EventManager events;
    Phase phaseNow;

    /**
     * Creates a new turn for player now
     * @param now player now
     * @param opponent player opponent
     * @throws Exception exception when draw phase cannot be instantiated
     */
    public Turn(Player now, Player opponent) throws Exception {
        this.playerNow = now;
        this.playerOpponent = opponent;
        createEventManager();
        initializeTurn();
        phaseNow = new DrawPhase(playerNow);
    }

    /**
     * Creates an event manager for disabling player
     * @throws Exception exception when GameController cannot be instantiated
     */
    void createEventManager() throws Exception {
        events = new EventManager(Event.DISABLEPLAYER, Event.ENABLEPLAYER);
        events.subscribe(Event.DISABLEPLAYER, GameController.getInstance());
        events.subscribe(Event.ENABLEPLAYER, GameController.getInstance());
    }

    /**
     * Initialize turn in AvatarDuel game
     * @throws Exception exception when the event cannot be notified
     */
    public void initializeTurn() throws Exception {
        disableOpponent();
        enablePlayerNow();
    }

    /**
     * Disable opponent player
     * @throws Exception exception when the event cannot be notified
     */
    public void disableOpponent() throws Exception {
        events.notify(Event.DISABLEPLAYER, playerOpponent.getName());
    }

    /**
     * Enable player now
     * @throws Exception exception when the event cannot be notified
     */
    public void enablePlayerNow() throws Exception {
        events.notify(Event.ENABLEPLAYER, playerNow.getName());
    }

    /**
     * Start the phase
     * @throws Exception exception when the phase cannot run normally
     */
    public void startPhase() throws Exception {
        phaseNow.run();
    }

    /**
     * Phase manager
     * @return next phase
     * @throws Exception exception when the phase cannot be instantiated
     */
    public Phase nextPhase() throws Exception {
        if (phaseNow instanceof DrawPhase) {
            phaseNow = new MainPhase(playerNow);
        } else if (phaseNow instanceof MainPhase) {
            phaseNow = new BattlePhase(playerNow, playerOpponent);
        } else if (phaseNow instanceof BattlePhase) {
            phaseNow = new EndPhase(playerNow);
        }
        return phaseNow;
    }
}
