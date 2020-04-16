package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventListener;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class Turn {
    Player playerNow;
    Player playerOpponent;
    EventManager events;
    Phase phaseNow;

    public Turn(Player now, Player opponent) throws Exception {
        this.playerNow = now;
        this.playerOpponent = opponent;
        createEventManager();
        initializeTurn();
        System.out.println("MASUK");
        phaseNow = new DrawPhase(playerNow);
    }

    void createEventManager() throws Exception {
        events = new EventManager(Event.DISABLEPLAYER, Event.ENABLEPLAYER);
        events.subscribe(Event.DISABLEPLAYER, GameController.getInstance());
        events.subscribe(Event.ENABLEPLAYER, GameController.getInstance());
    }

    public void initializeTurn() throws Exception {
        disableOpponent();
        enablePlayerNow();
    }

    public void disableOpponent() throws Exception {
        events.notify(Event.DISABLEPLAYER, playerOpponent.getName());
    }

    public void enablePlayerNow() throws Exception {
        events.notify(Event.ENABLEPLAYER, playerNow.getName());
    }

    public void startPhase() throws Exception {
        phaseNow.run();
    }

    public Phase nextPhase() throws Exception {
        if (phaseNow instanceof com.avatarduel.game.DrawPhase) {
            phaseNow = new MainPhase(playerNow);
        } else if (phaseNow instanceof  com.avatarduel.game.MainPhase) {
            phaseNow = new BattlePhase(playerNow, playerOpponent);
        }
        return phaseNow;
    }
}
