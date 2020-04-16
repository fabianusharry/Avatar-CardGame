package com.avatarduel.gui.controller;

import com.avatarduel.game.TurnManager;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventListener;
import javafx.scene.effect.DropShadow;
import com.avatarduel.gui.loader.*;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, EventListener {
    private static GameController instance = null; //Singleton attribute
 
    private Player P1;
    private Player P2;
    private Card placing;
    private TurnManager manager;

    private HandController p1HandController;
    private HandController p2HandController;

    private FieldController p1FieldController;
    private FieldController p2FieldController;

    public static GameController getInstance() throws Exception {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private GameController() throws Exception {
        this.P1 = new Player(MainMenuLoader.getInstance().getP1Name());
        this.P2 = new Player(MainMenuLoader.getInstance().getP2Name());
        this.manager = new TurnManager(P1, P2);
        this.placing = null;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P1Name.setText("Player 1 - " + P1.getName());
        P2Name.setText("Player 2 - " + P2.getName());
        try {
            initializeP1();
            initializeP2();
            manager.startTurn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeP1() throws Exception {
        HandLoader p1hand = new HandLoader(P1);
        p1HandController = p1hand.getController();
        FieldLoader p1field = new FieldLoader(P1, 1);
        p1FieldController = p1field.getController();
        P1Field.getChildren().add(p1field.getPane());
        cardView.getChildren().add(new CardLoader(P1.getHandCards().peek(0)).getPane());
        P1Element.getChildren().add(new PowerLoader(P1).getPane());
        P1HandCards.getChildren().add(p1hand.getPane());
        P1DeckSize.setText(String.valueOf(P1.getDeck().size()));
    }

    public void initializeP2() throws Exception {
        HandLoader p2hand = new HandLoader(P2);
        p2HandController = p2hand.getController();
        FieldLoader p2field = new FieldLoader(P2, 2);
        p2FieldController = p2field.getController();
        P2Field.getChildren().add(p2field.getPane());
        P2Element.getChildren().add(new PowerLoader(P2).getPane());
        P2HandCards.getChildren().add(p2hand.getPane());
        P2DeckSize.setText(String.valueOf(P2.getDeck().size()));
    }

    public void setEnableP1(Boolean isEnabled) throws Exception {
        p1HandController.reloadCardsPane();
        if (isEnabled) {
            P1deck.setDisable(false);
            p1HandController.setViewEnabled(true);
            P1gameStage.setDisable(false);
        } else {
            P1deck.setDisable(true);
            p1HandController.setEnableClick(false);
            p1HandController.flipCards();
            P1gameStage.setDisable(true);
            disableAllTextClickP1();
        }
    }

    public void setEnableP2(Boolean isEnabled) throws Exception {
        p2HandController.reloadCardsPane();
        if (isEnabled) {
            P2deck.setDisable(false);
            p2HandController.setViewEnabled(true);
            P2gameStage.setDisable(false);
        } else {
            P2deck.setDisable(true);
            p2HandController.setEnableClick(false);
            p2HandController.flipCards();
            P2gameStage.setDisable(true);
            disableAllTextClickP2();
        }
    }
    
    @FXML
    public void p1Draw() throws Exception {
        P1.draw();
        p1HandController.reloadCardsPane();
        P1DeckSize.setText(String.valueOf(P1.getDeck().size()));
        manager.getTurn().nextPhase().run();
    }

    @FXML
    public void p2Draw() throws Exception {
        P2.draw();
        p2HandController.reloadCardsPane();
        P2DeckSize.setText(String.valueOf(P2.getDeck().size()));
        manager.getTurn().nextPhase().run();
    }

    public void setStageTextP1(String value) {
        setPhaseText(value, drawPhaseP1, mainPhaseP1, battlePhaseP1, endPhaseP1);
    }

    public void setStageTextP2(String value) {
        setPhaseText(value, drawPhaseP2, mainPhaseP2, battlePhaseP2, endPhaseP2);
    }

    public void initializePhase(Pane playerPane) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.colorProperty().setValue(Color.RED);
        playerPane.setEffect(dropShadow);
    }

    private void setPhaseText(String value, Text drawPhaseP1, Text mainPhaseP1, Text battlePhaseP1, Text endPhaseP1) {
        switch (value) {
            case "draw":
                drawPhaseP1.setStyle("-fx-fill: red;");
                break;
            case "main":
                drawPhaseP1.setStyle("-fx-fill: black;");
                mainPhaseP1.setStyle("-fx-fill: red;");
                break;
            case "battle":
                mainPhaseP1.setStyle("-fx-fill: black;");
                battlePhaseP1.setStyle("-fx-fill: red;");
                break;
            default:
                drawPhaseP1.setStyle("-fx-fill: black;");
                mainPhaseP1.setStyle("-fx-fill: black;");
                battlePhaseP1.setStyle("-fx-fill: black;");
                break;
        }
    }

    public Card getCardPlacing(){
        return this.placing;
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

    public void disable(Text text,boolean value){
        if(value){
            text.setOnMouseClicked(null);
            System.out.println(text.getId());
            System.out.println("Berhaisl atur mouse clicked JADI FALSE");
        }
        else{
            text.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                try {
                    manager.getTurn().nextPhase().run();
                    System.out.println(text.getId());
                    System.out.println("Berhasil atur mouse clicked jadi bisa");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public void disableAllTextClickP1() {
        drawPhaseP1.setDisable(true);
        mainPhaseP1.setDisable(true);
        battlePhaseP1.setDisable(true);
        endPhaseP1.setDisable(true);
    }

    public void disableAllTextClickP2() {
        drawPhaseP2.setDisable(true);
        mainPhaseP2.setDisable(true);
        battlePhaseP2.setDisable(true);
        endPhaseP2.setDisable(true);
    }

    public void nextPhase(javafx.event.Event evt) throws Exception {
        manager.getTurn().nextPhase().run();
    }

    public void disable(Pane pane, boolean value) {
        pane.setDisable(value);
    }

    @Override
    public void update(Event eventType, Object value) throws Exception {
        if (eventType.equals(Event.CHANGE_CARD_VIEW)) {
            setCardView((Card) value);
        } else if (eventType.equals(Event.PASS_CARD)) {
            this.placing = (Card) value;
        }
        else if (eventType.equals((Event.UPDATE_POWER))) {
            if (value.equals(P1.getName())) {
                reload(P1Element, new PowerLoader(P1).getPane());
            } else {
                reload(P2Element, new PowerLoader(P2).getPane());
            }
        } else if(eventType.equals(Event.DISABLEPLAYER)){
            if(value.equals(P1.getName())){
                setEnableP1(false);
                P1gameStage.setEffect(null);
                setStageTextP1("");
                P1Turn.setProgress(0);
            } else{
                setEnableP2(false);
                P2gameStage.setEffect(null);
                setStageTextP2("");
                P2Turn.setProgress(0);
            }
        } else if(eventType.equals(Event.ENABLEPLAYER)){
            if(value.equals(P1.getName())){
                setEnableP1(true);
                initializePhase(P1gameStage);
                setStageTextP1("draw");
                P1Turn.setProgress(-1);
            } else{
                setEnableP2(true);
                initializePhase(P2gameStage);
                setStageTextP2("draw");
                P2Turn.setProgress(-1);
            }
        } else if (eventType.equals(Event.DRAW_PHASE)) {
            if (value.equals(P1.getName())) {
                P1Field.setDisable(true);
                p1HandController.setEnableClick(false);
                p1HandController.setDisableLand(false);
            } else {
                P2Field.setDisable(true);
                p2HandController.setEnableClick(false);
                p2HandController.setDisableLand(false);
            }
        } else if (eventType.equals(Event.MAIN_PHASE)) {
            if (value.equals(P1.getName())) {
                setStageTextP1("main");
                P1deck.setDisable(true);
                P1Field.setDisable(false);
                p1HandController.setEnableClick(true);
                disable(mainPhaseP1, true);
                disable(battlePhaseP1, false);
            } else {
                setStageTextP2("main");
                P2deck.setDisable(true);
                P2Field.setDisable(false);
                p2HandController.setEnableClick(true);
                disable(mainPhaseP2, true);
                disable(battlePhaseP2, false);
            }
        } else if (eventType.equals(Event.BATTLE_PHASE)) {
            if (value.equals(P1.getName())) {
                setStageTextP1("battle");
                p1HandController.setEnableClick(false);
                p1HandController.setViewEnabled(false);
                disable(battlePhaseP1, true);
                disable(endPhaseP1, false);
            } else {
                setStageTextP2("battle");
                p2HandController.setEnableClick(false);
                p2HandController.setViewEnabled(false);
                disable(battlePhaseP2, true);
                disable(endPhaseP2, false);
            }
        } else if (eventType.equals(Event.END_PHASE)) {
            manager.changeTurn();
        } else if (eventType.equals(Event.GOT_CARD)) {
            if(value.equals(P1.getName())){
                System.out.println("UDA ADA KARTU READY DITARUH");
                System.out.println(this.placing.getClass());
                p1HandController.setEnableClick(false);
                p1FieldController.setOnClick("placeCard");
                p2FieldController.disable();
            } else {
                p2HandController.setEnableClick(false);
                p2FieldController.setOnClick("placeCard");
                p1FieldController.disable();
            }
        } 
        else if(eventType.equals(Event.CARD_PLACED)){
            if(value.equals(P1.getName())){
               p1HandController.setEnableClick(true);
               p1FieldController.disable();
            }
            else{
                p2HandController.setEnableClick(true);
                p2FieldController.disable();
            }
        }
    }
}
