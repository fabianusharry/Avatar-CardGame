package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.loader.CardLoader;
import com.avatarduel.gui.loader.PowerLoader;
import com.avatarduel.model.Power;
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
    @FXML private Pane P1Element;
    @FXML private Pane P1gameStage;
    @FXML private Pane P1deck;
    @FXML private Pane P1HandCards;
    @FXML private Pane P1Field;
    @FXML private Pane P2Element;
    @FXML private Pane P2gameStage;
    @FXML private Pane P2deck;
    @FXML private Pane P2HandCards;
    @FXML private Pane P2Field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P1Name.setText("Player 1 - " + game.P1.getName());
        P2Name.setText("Player 2 - " + game.P2.getName());
        // TEST
        try {
            CardLoader p1card = new CardLoader(game.P1.takeCard(0));
            PowerLoader p1power = new PowerLoader(game.P1);
            cardView.getChildren().add(p1card.getPane());
            P1Element.getChildren().add(p1power.getPane());
//            p1power.render();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
