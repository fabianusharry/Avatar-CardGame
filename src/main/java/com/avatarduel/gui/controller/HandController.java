package com.avatarduel.gui.controller;

import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.BackCardLoader;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HandController implements Initializable {
    private Player player;
    private EventManager events;
    private boolean viewEnabled;

    @FXML private List<Pane> cards;

    public HandController(Player player) throws Exception {
        this.player = player;
        viewEnabled = true;
        events = new EventManager(Event.CHANGE_CARD_VIEW, Event.TAKE_HAND_CARD, Event.UPDATE_POWER);
        events.subscribe(Event.CHANGE_CARD_VIEW, GameController.getInstance());
        events.subscribe(Event.TAKE_HAND_CARD, GameController.getInstance());
        events.subscribe(Event.UPDATE_POWER, GameController.getInstance());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            for (int i = 0; i < player.getHandCards().size(); i++) {
                cards.get(i).getChildren().add(new MiniCardLoader(player.getHandCards().peek(i)).getPane());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Card getCard(javafx.event.Event evt) throws Exception {
        String id = evt.getSource().toString().replaceAll("[^0-9]",""); // ambil integernya aja
        Card takenCard = player.takeCard(Integer.parseInt(id));
        if (takenCard instanceof com.avatarduel.model.card.Land) {
            events.notify(Event.UPDATE_POWER, player.getName());
        }
        events.notify(Event.TAKE_HAND_CARD, player.getName());
        return takenCard;
    }

    @FXML
    public void showCard(javafx.event.Event evt) throws Exception {
        if (viewEnabled) {
            String id = evt.getSource().toString().replaceAll("[^0-9]","");
            events.notify(Event.CHANGE_CARD_VIEW, player.getHandCards().peek(Integer.parseInt(id)));
        }
    }

    public void flipCards() throws IOException {
        for (int i = 0; i < player.getHandCards().size(); i++) {
            cards.get(i).getChildren().clear();
            cards.get(i).getChildren().add(new BackCardLoader().getPane());
        }
        this.viewEnabled = false;
    }

    public void enable(boolean value) {
        if (value) {
            for (int i = 0; i < player.getHandCards().size(); i++) {
                cards.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    try {
                        getCard(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }   
                });
            }
        } else {
            for (int i = 0; i < player.getHandCards().size(); i++) {
                cards.get(i).setOnMouseClicked(null);
            }
        }
    }
}
