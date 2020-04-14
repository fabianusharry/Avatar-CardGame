package com.avatarduel.gui.controller;

import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventListener;
import com.avatarduel.gui.loader.*;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, EventListener {
    private static GameController instance = null; //Singleton attribute

    private Player P1;
    private Player P2;

    public static GameController getInstance() throws Exception {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private GameController() throws Exception {
        this.P1 = new Player(MainMenuLoader.getInstance().getP1Name());
        this.P2 = new Player(MainMenuLoader.getInstance().getP2Name());
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
    @FXML private ImageView P1deck;
    @FXML private Text P1DeckSize;
    @FXML private Pane P1HandCards;
    @FXML private Pane P1Field;
    @FXML private Text drawPhaseP1;
    @FXML private Text mainPhaseP1;
    @FXML private Text battlePhaseP1;
    @FXML private Text endPhaseP1;
    @FXML private Pane P1Blocker;
    @FXML private Pane P2Element;
    @FXML private Pane P2gameStage;
    @FXML private ImageView P2deck;
    @FXML private Text P2DeckSize;
    @FXML private Pane P2HandCards;
    @FXML private Pane P2Field;
    @FXML private Text drawPhaseP2;
    @FXML private Text mainPhaseP2;
    @FXML private Text battlePhaseP2;
    @FXML private Text endPhaseP2;
    @FXML private Pane P2Blocker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P1Name.setText("Player 1 - " + P1.getName());
        P2Name.setText("Player 2 - " + P2.getName());
        // TEST
        try {
            CardLoader p1card = new CardLoader(P1.getHandCards().peek(0));
            PowerLoader p1power = new PowerLoader(P1);
            HandLoader p1hand = new HandLoader(P1.getHandCards(), "P1");
            P1.field.getCharacterField().placeCard(1, P1.takeCard(0));
            FieldPlayer1Loader p1field = new FieldPlayer1Loader(P1.field);
            P1Field.getChildren().add(p1field.getPane());
            cardView.getChildren().add(p1card.getPane());
            P1Element.getChildren().add(p1power.getPane());
            P1HandCards.getChildren().add(p1hand.getPane());
//            setEnableP2(true);
//            setEnableP1(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setEnableP1(Boolean isEnabled) {
        if (isEnabled) {
            P1Blocker.setDisable(false); //blocker dilepas
        } else {
            P1Blocker.setDisable(true); //pasang blocker
        }
    }

    public void setEnableP2(Boolean isEnabled) {
        if (isEnabled) {
            P2Blocker.setDisable(false); //blocker dilepas
        } else {
            P2Blocker.setDisable(true); //pasang blocker
        }
    }

    @FXML
    public void p1Draw() throws Exception {
        P1.draw();
        reload(P1HandCards, new HandLoader(P1.getHandCards(), "P1").getPane());
    }

    @FXML
    public void p2Draw() {
        System.out.println("MASUK2");
    }

    public Pane getCardView() {
        return cardView;
    }

    public void setCardView(Card card) throws IOException {
        CardLoader newCardView = new CardLoader(card);
        cardView.getChildren().add(newCardView.getPane());
    }

    public void reload(Pane pane, Pane newNode) {
        pane.getChildren().clear();
        pane.getChildren().add(newNode);
    }

    @Override
    public void update(Event eventType, Object value) throws Exception {
        if (eventType.equals(Event.CHANGE_CARD_VIEW)) {
            setCardView((Card) value);
        } else if (eventType.equals(Event.TAKE_HAND_CARD)) {
            if (value.equals("P1")) {

                reload(P1HandCards, new HandLoader(P1.getHandCards(), "P1").getPane());
            }
        }
    }
}
