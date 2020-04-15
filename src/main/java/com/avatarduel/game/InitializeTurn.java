package com.avatarduel.game;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.model.Player;

public class InitializeTurn {
    Player playerNow;
    Player playerOpponent;
    EventManager events;

    public InitializeTurn(Player now, Player opponent) throws Exception {
        this.playerNow = now;
        this.playerOpponent = opponent;
        events = new EventManager(Event.DISABLEPLAYER, Event.ENABLEPLAYER);
        events.subscribe(Event.DISABLEPLAYER, GameController.getInstance());
        events.subscribe(Event.ENABLEPLAYER, GameController.getInstance());
        disableOpponent();
        enablePlayerNow();
    }

    public void disableOpponent() throws Exception {
        events.notify(Event.DISABLEPLAYER, playerOpponent.getName());
    }

    public void enablePlayerNow() throws Exception {
        events.notify(Event.ENABLEPLAYER, playerNow.getName());
    }
}
