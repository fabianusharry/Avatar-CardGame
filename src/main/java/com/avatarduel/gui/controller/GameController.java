package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.loader.CardLoader;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private AvatarDuel game;

    public GameController(AvatarDuel game) {
        this.game = game;
    }

    @FXML private Pane cardPane;
    @FXML private ProgressBar P1HPBar;
    @FXML private ProgressBar P2HPBar;
    @FXML private Label P1Name;
    @FXML private Label P2Name;
    @FXML private Pane cardView;
    @FXML private ProgressBar P1Turn;
    @FXML private ProgressBar P2Turn;
    @FXML private Label P1HP;
    @FXML private Label P2HP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P1Name.setText("Player 1 - " + game.P1.getName());
        P2Name.setText("Player 2 - " + game.P2.getName());
        // TEST
        try {
            CardLoader test = new CardLoader(game.P1.takeCard(0));
            cardView.getChildren().add(test.getPane());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
