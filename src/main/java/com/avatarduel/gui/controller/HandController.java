package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
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

    public HandController(HandCards hand) {
        this.hand = hand;
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
}
