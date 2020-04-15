package com.avatarduel.gui.controller;

import com.avatarduel.game.Turn;
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

    private HandController p1HandController;
    private HandController p2HandController;

    private int turn;
    public static GameController getInstance() throws Exception {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private GameController() throws Exception {
        this.P1 = new Player(MainMenuLoader.getInstance().getP1Name());
        this.P2 = new Player(MainMenuLoader.getInstance().getP2Name());
        this.turn = 1;
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
            startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeP1() throws Exception {
        HandLoader p1hand = new HandLoader(P1);
        p1HandController = p1hand.getController();
        FieldPlayer1Loader p1field = new FieldPlayer1Loader(P1.field);
        P1Field.getChildren().add(p1field.getPane());
        cardView.getChildren().add(new CardLoader(P1.getHandCards().peek(0)).getPane());
        P1Element.getChildren().add(new PowerLoader(P1).getPane());
        P1HandCards.getChildren().add(p1hand.getPane());
    }

    public void initializeP2() throws Exception {
        HandLoader p2hand = new HandLoader(P2);
        p2HandController = p2hand.getController();
        FieldPlayer2Loader p2field = new FieldPlayer2Loader(P2.field);
        P2Field.getChildren().add(p2field.getPane());
        P2Element.getChildren().add(new PowerLoader(P2).getPane());
        P2HandCards.getChildren().add(p2hand.getPane());
    }

    public void setEnableP1(Boolean isEnabled) throws Exception {
        HandLoader hand = new HandLoader(P1);
        p1HandController = hand.getController();
        reload(P1HandCards, hand.getPane());
        if (isEnabled) {
            P1deck.setDisable(false);
        } else {
            P1deck.setDisable(true);
            p1HandController.enable(false);
            p1HandController.flipCards();
        }
    }

    public void setEnableP2(Boolean isEnabled) throws Exception {
        HandLoader hand = new HandLoader(P2);
        p2HandController = hand.getController();
        reload(P2HandCards, hand.getPane());
        if (isEnabled) {
            P2deck.setDisable(false);
        } else {
            P2deck.setDisable(true);
            p2HandController.enable(false);
            p2HandController.flipCards();
        }
    }
    
    public void EndTurn(){
        this.turn++;
    }
    
    @FXML
    public void p1Draw() throws Exception {
        P1.draw();
        reload(P1HandCards, new HandLoader(P1).getPane());
        P1DeckSize.setText(String.valueOf(P1.getDeck().size()));
        //notify PhaseController kalau drawPhase uda dilakukan
        BattlePhase();
    }

    @FXML
    public void p2Draw() throws Exception {
        P2.draw();
        reload(P2HandCards, new HandLoader(P2).getPane());
        P1DeckSize.setText(String.valueOf(P1.getDeck().size()));
        //notify PhaseController kalau drawPhase uda dilakukan
        BattlePhase();
    }

    public void BattlePhase() throws Exception{
        if(turn%2==1){
            System.out.println("Berhaisl memasuki battle phase player 1");
        }
        else{
            System.out.println("Berhasil memasuki battle phase player 2");
        }
        EndPhase();
        
    }
    
    public void EndPhase() throws Exception{
        System.out.println("ENDPHASEss");
        EndTurn();
        startGame();
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
            case "end":
                battlePhaseP1.setStyle("-fx-fill: black;");
                endPhaseP1.setStyle("-fx-fill: red;");
                break;
            default:
                drawPhaseP1.setStyle("-fx-fill: black;");
                mainPhaseP1.setStyle("-fx-fill: black;");
                battlePhaseP1.setStyle("-fx-fill: black;");
                endPhaseP1.setStyle("-fx-fill: black;");
                break;
        }
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

    public void disable(Pane pane, boolean value) {
        pane.setDisable(value);
    }
    
    public void startGame() throws Exception{
        if(turn%2==1){
            new Turn(P1, P2);
            System.out.println("Draw phase P1");
        }
        else{
            new Turn(P2, P1);
            System.out.println("Draw phase P2");
        }
    }

    @Override
    public void update(Event eventType, Object value) throws Exception {
        if (eventType.equals(Event.CHANGE_CARD_VIEW)) {
            setCardView((Card) value);
        } else if (eventType.equals(Event.TAKE_HAND_CARD)) {
            if (value.equals(P1.getName())) {
                reload(P1HandCards, new HandLoader(P1).getPane());
            } else {
                reload(P2HandCards, new HandLoader(P2).getPane());
            }
        } else if (eventType.equals((Event.UPDATE_POWER))) {
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
                p1HandController.enable(false);
            } else {
                P2Field.setDisable(true);
                p2HandController.enable(false);
            }
        }
    }
}
