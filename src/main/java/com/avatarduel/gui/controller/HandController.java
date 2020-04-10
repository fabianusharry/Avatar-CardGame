package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventListener;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.CardLoader;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.gui.loader.PowerLoader;
import com.avatarduel.model.cards.HandCards;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HandController implements Initializable {
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

    public HandController(HandCards hand) throws Exception {
        this.hand = hand;
        events = new EventManager(Event.ChangeCardView);
        events.subscribe(Event.ChangeCardView, GameController.getInstance());
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
    public void showCard0() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(0));
    }

    @FXML
    public void showCard1() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(1));
    }

    @FXML
    public void showCard2() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(2));
    }

    @FXML
    public void showCard3() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(3));
    }

    @FXML
    public void showCard4() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(4));
    }

    @FXML
    public void showCard5() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(5));
    }

    @FXML
    public void showCard6() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(6));
    }

    @FXML
    public void showCard7() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(7));
    }

    @FXML
    public void showCard8() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(8));
    }

    @FXML
    public void showCard9() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(9));
    }

    @FXML
    public void showCard10() throws Exception {
        events.notify(Event.ChangeCardView, hand.peek(9));
    }
}
