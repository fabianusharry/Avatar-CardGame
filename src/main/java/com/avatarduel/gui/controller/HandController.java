package com.avatarduel.gui.controller;

import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.cards.HandCards;
import javafx.scene.control.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HandController implements Initializable {
    private String playerNumber;
    private HandCards hand;
    private EventManager events;

    @FXML private Pane card0;
    @FXML private Pane card1;
    @FXML private Pane card2;
    @FXML private Pane card3;
    @FXML private Pane card4;
    @FXML private Pane card5;
    @FXML private Pane card6;
    @FXML private Pane card7;
    @FXML private Pane card8;
    @FXML private Pane card9;
    @FXML private Pane card10;

    public HandController(HandCards hand, String playerNumber) throws Exception {
        this.playerNumber = playerNumber;
        this.hand = hand;
        events = new EventManager(Event.CHANGE_CARD_VIEW, Event.TAKE_HAND_CARD);
        events.subscribe(Event.CHANGE_CARD_VIEW, GameController.getInstance());
        events.subscribe(Event.TAKE_HAND_CARD, GameController.getInstance());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            card0.getChildren().add(new MiniCardLoader(hand.peek(0)).getPane());
            card1.getChildren().add(new MiniCardLoader(hand.peek(1)).getPane());
            card2.getChildren().add(new MiniCardLoader(hand.peek(2)).getPane());
            card3.getChildren().add(new MiniCardLoader(hand.peek(3)).getPane());
            card4.getChildren().add(new MiniCardLoader(hand.peek(4)).getPane());
            card5.getChildren().add(new MiniCardLoader(hand.peek(5)).getPane());
            card6.getChildren().add(new MiniCardLoader(hand.peek(6)).getPane());
            card7.getChildren().add(new MiniCardLoader(hand.peek(7)).getPane());
            card8.getChildren().add(new MiniCardLoader(hand.peek(8)).getPane());
            card9.getChildren().add(new MiniCardLoader(hand.peek(9)).getPane());
            card10.getChildren().add(new MiniCardLoader(hand.peek(10)).getPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Card getCard(javafx.event.Event evt) throws Exception {
        String id = evt.getSource().toString().replaceAll("[^0-9]","");
        Card takenCard = hand.take(Integer.parseInt(id));
        events.notify(Event.TAKE_HAND_CARD, playerNumber);
        return takenCard;
    }

    @FXML
    public void showCard(javafx.event.Event evt) throws Exception {
        String id = evt.getSource().toString().replaceAll("[^0-9]","");
        events.notify(Event.CHANGE_CARD_VIEW, hand.peek(Integer.parseInt(id)));
    }
}
