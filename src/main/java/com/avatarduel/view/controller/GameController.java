package com.avatarduel.view.controller;

import com.avatarduel.game.TurnManager;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventListener;
import com.avatarduel.model.SummonedCard;
import javafx.scene.effect.DropShadow;
import com.avatarduel.view.loader.*;
import com.avatarduel.model.Player;
import com.avatarduel.model.SummonedCharacter;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for AvatarDuel game
 */
public class GameController implements Initializable, EventListener {
    private static GameController instance = null; //Singleton attribute
 
    private Player P1;
    private Player P2;
    private Card placing;
    private Skill skillAttaching;
    private String skillLocation;
    private String modifyType;
    private String modifyLocation;
    private SummonedCard selecting;
    private String selectingId;
    private TurnManager manager;
    private boolean endGame;

    private HandController p1HandController;
    private HandController p2HandController;

    private FieldController p1FieldController;
    private FieldController p2FieldController;

    /**
     * Singleton design pattern implementation
     * @return instance
     * @throws Exception exception when a new GameController cannot be instantiated
     */
    public static GameController getInstance() throws Exception {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    /**
     * Creates a new GameController
     * @throws Exception
     */
    private GameController() throws Exception {
        this.P1 = new Player(MainMenuLoader.getInstance().getP1Name());
        this.P2 = new Player(MainMenuLoader.getInstance().getP2Name());
        this.manager = new TurnManager(P1, P2);
        this.placing = null;
        this.selecting = null;
        this.selectingId = null;
    }

    @FXML public Pane cardPane;
    @FXML public ProgressBar P1HPBar;
    @FXML public ProgressBar P2HPBar;
    @FXML public Label P1Name;
    @FXML public Label P2Name;
    @FXML public Pane cardView;
    @FXML public ProgressBar P1Turn;
    @FXML public ProgressBar P2Turn;
    @FXML public Label P1HP;
    @FXML public Label P2HP;
    @FXML public Pane P1Element;
    @FXML public Pane P1gameStage;
    @FXML public ImageView P1deck;
    @FXML public Text P1DeckSize;
    @FXML public Pane P1HandCards;
    @FXML public Pane P1Field;
    @FXML public Text drawPhaseP1;
    @FXML public Text mainPhaseP1;
    @FXML public Text battlePhaseP1;
    @FXML public Text endPhaseP1;
    @FXML public Pane P2Element;
    @FXML public Pane P2gameStage;
    @FXML public ImageView P2deck;
    @FXML public Text P2DeckSize;
    @FXML public Pane P2HandCards;
    @FXML public Pane P2Field;
    @FXML public Text drawPhaseP2;
    @FXML public Text mainPhaseP2;
    @FXML public Text battlePhaseP2;
    @FXML public Text endPhaseP2;

    /**
     * Initialize all Game attributes
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P1Name.setText("Player 1 - " + P1.getName());
        P2Name.setText("Player 2 - " + P2.getName());
        try {
            initializeP1();
            initializeP2();
            manager.startTurn();
        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    /**
     * Initialize Player1
     * @throws Exception exception when Player1 pane cannot be instantiated
     */
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

    /**
     * Initialize Player2
     * @throws Exception exception when Player2 pane cannot be instantiated
     */
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

    /**
     * Set enable player1
     * @param isEnabled isEnabled
     * @throws Exception exception when Player1 pane cannot be disabled
     */
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

    /**
     * Set enable player2
     * @param isEnabled isEnabled
     * @throws Exception exception when Player2 pane cannot be disabled
     */
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

    /**
     * Handle button click for p1 draw
     * @throws Exception exception when Player1 pane cannot draw normally
     */
    @FXML
    public void p1Draw() throws Exception {
        P1.draw();
        p1HandController.reloadCardsPane();
        P1DeckSize.setText(String.valueOf(P1.getDeck().size()));
        manager.getTurn().nextPhase().run();
    }

    /**
     * Handle button click for p2 draw
     * @throws Exception exception when Player2 pane cannot draw normally
     */
    @FXML
    public void p2Draw() throws Exception {
        P2.draw();
        p2HandController.reloadCardsPane();
        P2DeckSize.setText(String.valueOf(P2.getDeck().size()));
        manager.getTurn().nextPhase().run();
    }

    /**
     * Set stage text in Player1
     * @param value value
     */
    public void setStageTextP1(String value) {
        setPhaseText(value, drawPhaseP1, mainPhaseP1, battlePhaseP1, endPhaseP1);
    }

    /**
     * Set stage text in Player2
     * @param value value
     */
    public void setStageTextP2(String value) {
        setPhaseText(value, drawPhaseP2, mainPhaseP2, battlePhaseP2, endPhaseP2);
    }

    /**
     * Initialize phase for player
     * @param playerPane playerPane
     */
    public void initializePhase(Pane playerPane) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.colorProperty().setValue(Color.RED);
        playerPane.setEffect(dropShadow);
    }

    /**
     * Set player phase text with value
     * @param value value
     * @param drawPhase drawPhaseText
     * @param mainPhase mainPhaseText
     * @param battlePhase battlePhaseText
     * @param endPhase endPhaseText
     */
    private void setPhaseText(String value, Text drawPhase, Text mainPhase, Text battlePhase, Text endPhase) {
        switch (value) {
            case "draw":
                drawPhase.setStyle("-fx-fill: red;");
                break;
            case "main":
                drawPhase.setStyle("-fx-fill: black;");
                mainPhase.setStyle("-fx-fill: red;");
                break;
            case "battle":
                mainPhase.setStyle("-fx-fill: black;");
                battlePhase.setStyle("-fx-fill: red;");
                break;
            default:
                drawPhase.setStyle("-fx-fill: black;");
                mainPhase.setStyle("-fx-fill: black;");
                battlePhase.setStyle("-fx-fill: black;");
                break;
        }
    }

    /**
     * Check if the game is in end condition
     * @return true if end game
     */
    public boolean isEndGame() {
        endGame = P1.getHP() <= 0 || P2.getHP() <= 0;
        return endGame;
    }

    /**
     * Get card placing
     * @return placing
     */
    public Card getCardPlacing(){
        return this.placing;
    }

    /**
     * Get card selected
     * @return selecting
     */
    public SummonedCard getCardSelected(){
        return this.selecting;
    }

    /**
     * Get card selected paneId
     * @return selectingId
     */
    public String getSelectedPaneID(){
        return this.selectingId;
    }

    /**
     * Get skill location in field
     * @return skillLocation
     */
    public String getSkillLocation(){
        return this.skillLocation;
    }

    /**
     * Get skill placing
     * @return skillPlacing
     */
    public Skill getSkillPlacing(){
        return this.skillAttaching;
    }

    /**
     * get modify location of a card in field
     * @return modifyLocation
     */
    public String getModifyLocation(){
        return modifyLocation;
    }

    /**
     * Get modify type of a card in field
     * @return modifyType
     */
    public String getModifyType(){
        return modifyType;
    }

    /**
     * Get Big cardView pane
     * @return cardView
     */
    public Pane getCardView() {
        return cardView;
    }

    /**
     * Get player1
     * @return p1
     */
    public Player getP1() {
        return P1;
    }

    /**
     * Get player2
     * @return p2
     */
    public Player getP2(){
        return P2;
    }

    /**
     * Get turn manager
     * @return manager
     */
    public TurnManager getManager() {
        return manager;
    }

    /**
     * Get player1 handcontroller
     * @return p1handcontroller
     */
    public HandController getP1HandController() {
        return p1HandController;
    }

    /**
     * Get player2 handcontroller
     * @return p2handcontroller
     */
    public HandController getP2HandController() {
        return p2HandController;
    }

    /**
     * Get player1 fieldcontroller
     * @return p1fieldcontroller
     */
    public FieldController getP1FieldController(){
        return p1FieldController;
    }

    /**
     * Get player2 fieldcontroller
     * @return p2fieldcontroller
     */
    public FieldController getP2FieldController(){
        return p2FieldController;
    }

    /**
     * Handle keyboard SHIFT button click to delete card selected
     * @param event event
     * @throws Exception exception when onKeyPressed event fails
     */
    @FXML
    public void handleOnKeyPressed(KeyEvent event) throws Exception{
        if(event.getCode().equals(KeyCode.SHIFT)){
            if(p1FieldController.delete()){
                p1FieldController.deleteCard();
            } else if (p2FieldController.delete()){
                p2FieldController.deleteCard();
            }
        }
    }

    /**
     * Set the Big CardView with card
     * @param card card
     * @throws IOException exception when set cardView operation fails
     */
    public void setCardView(Card card) throws IOException {
        CardLoader newCardView = new CardLoader(card);
        cardView.getChildren().add(newCardView.getPane());
    }

    /**
     * Set the Big CardView with SummonedCard
     * @param summonedCard summonedCard
     * @throws IOException exception when setCardView operation fails
     */
    public void setCardView(SummonedCard summonedCard) throws IOException {
        CardLoader newCardView = new CardLoader(summonedCard.getCharacter());
        newCardView.setSkillAttached(summonedCard.getSkillAttached());
        cardView.getChildren().add(newCardView.getPane());
    }

    /**
     * Reload pane with new children node
     * @param pane pane
     * @param newNode new children node
     */
    public void reload(Pane pane, Pane newNode) {
        pane.getChildren().clear();
        pane.getChildren().add(newNode);
    }

    /**
     * Disable text
     * @param text text
     * @param value boolean value
     */
    public void disable(Text text,boolean value) {
        if(value) {
            text.setDisable(true);
        } else {
            text.setDisable(false);
        }
    }

    /**
     * Set HP Bar for player1
     * @param HP hp
     */
    public void setP1HPBar(int HP) {
        P1HPBar.setProgress((1.25*HP)/100);
    }
    /**
     * Set HP Bar for player2
     * @param HP hp
     */
    public void setP2HPBar(int HP) {
        P2HPBar.setProgress((1.25*HP)/100);
    }

    /**
     * disable click for player1
     */
    public void disableAllTextClickP1() {
        mainPhaseP1.setDisable(true);
        battlePhaseP1.setDisable(true);
        endPhaseP1.setDisable(true);
    }

    /**
     * disable click for player2
     */
    public void disableAllTextClickP2() {
        mainPhaseP2.setDisable(true);
        battlePhaseP2.setDisable(true);
        endPhaseP2.setDisable(true);
    }

    /**
     * next phase handling
     * @throws Exception exception when instantiating new phase fails
     */
    public void nextPhase() throws Exception {
        if (!endGame) {
            manager.getTurn().nextPhase().run();
        }

    }

    /**
     * Disable pane
     * @param pane pane
     * @param value boolean value
     */
    public void disable(Pane pane, boolean value) {
        pane.setDisable(value);
    }

    /**
     * Update game with specific event
     * @param eventType eventType
     * @param value object passed to GameController
     * @throws Exception exception when update cannot be done normally
     */
    @Override
    public void update(Event eventType, Object value) throws Exception {
        if (eventType.equals(Event.CHANGE_CARD_VIEW)) {
            if (value instanceof Card) {
                setCardView((Card) value);
            } else {
                setCardView((SummonedCard) value);
            }
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
        } else if (eventType.equals(Event.GOT_CARD)) {
            if(value.equals(P1.getName())){
                System.out.println(this.placing.getClass());
                p1HandController.setEnableClick(false);
                p1FieldController.setEnableClick(true);
                p1FieldController.setOnClick("placeCard");
                p2FieldController.setOnClick("placeCard");
                p2FieldController.setEnableClick(false);
                disableAllTextClickP1();
            } else {
                p2HandController.setEnableClick(false);
                p2FieldController.setEnableClick(true);
                p2FieldController.setOnClick("placeCard");
                p1FieldController.setOnClick("placeCard");
                p1FieldController.setEnableClick(false);
                disableAllTextClickP2();
            }
        } 
        else if(eventType.equals(Event.CARD_PLACED)){
            if(value.equals(P1.getName())){
               p1HandController.setEnableClick(true);
               p1FieldController.reloadBorder();
               p2FieldController.reloadBorder();
               p2FieldController.reloadFieldPane();
               p1FieldController.reloadFieldPane();
               p1FieldController.enableAll();
               p1FieldController.setOnClick("modify");
               p2FieldController.setOnClick("modify");
               p2FieldController.disableAll();
               disable(battlePhaseP1, false);
            }
            else{
                p2HandController.setEnableClick(true);
                p1FieldController.reloadBorder();
                p2FieldController.reloadBorder();
                p1FieldController.reloadFieldPane();
                p2FieldController.reloadFieldPane();
                p2FieldController.setOnClick("modify");
                p1FieldController.setOnClick("modify");
                p2FieldController.enableAll();  
                p1FieldController.disableAll();
                disable(battlePhaseP2, false);
            }
        }
        else if(eventType.equals(Event.PASS_SELECTED_CARD)){
            this.selecting = (SummonedCharacter) value;
        }
        else if(eventType.equals(Event.PASS_SELECTED_PANEID)){
            this.selectingId = (String) value;
        }
        else if(eventType.equals(Event.SELECTEDCARD)){
            if(value.equals(P1.getName())){
                if (P2.field.getCharacterField().isEmpty()) {
                    p1FieldController.setOnClick("attackHP");
                    p2FieldController.setOnClick("attackHP");
                } else {
                    p1FieldController.setOnClick("useCard");
                    p2FieldController.setOnClick("useCard");
                }
                //Disable seluruh p1FieldController disable skill p2FieldController
                p1FieldController.setEnableClick(false);
                p2FieldController.setEnableClick(false);
                p2FieldController.enableCharacter();
                p1FieldController.enableSpecific(this.selectingId.substring(0,selectingId.indexOf(' ')));
            }
            else{
                if (P1.field.getCharacterField().isEmpty()) {
                    p1FieldController.setOnClick("attackHP");
                    p2FieldController.setOnClick("attackHP");
                } else {
                    p1FieldController.setOnClick("useCard");
                    p2FieldController.setOnClick("useCard");
                }
                p2FieldController.setEnableClick(false);
                p1FieldController.setEnableClick(false);
                p1FieldController.enableCharacter();
                p2FieldController.enableSpecific(this.selectingId.substring(0,selectingId.indexOf(' ')));

                }
        } else if (eventType.equals(Event.RESET_SELECT_CARD)) {
            if (value.equals((P1.getName()))) {
                p1FieldController.setEnableClick(false);
                p2FieldController.setEnableClick(true);
                p2FieldController.setOnClick("selectCard");
                p1FieldController.setOnClick("selectCard");
            } else {
                p1FieldController.setEnableClick(true);
                p2FieldController.setEnableClick(false);
                p1FieldController.setOnClick("selectCard");
                p2FieldController.setOnClick("selectCard");
            }
            p1FieldController.reloadBorder();
            p2FieldController.reloadBorder();
        } else if(eventType.equals(Event.SKILL_LOCATION)){
            skillLocation = (String) value;
        } else if(eventType.equals(Event.SKILL_PLACING)){
            skillAttaching = (Skill) value;
        } else if(eventType.equals(Event.ATTACHING_SKILL)){
            p1FieldController.setEnableClick(false);
            p2FieldController.setEnableClick(false);
            p1FieldController.enableCharacter();
            p2FieldController.enableCharacter();
            p1FieldController.setOnClick("attachSkill");
            p2FieldController.setOnClick("attachSkill");  
        } else if(eventType.equals(Event.MODIFYING)){
            this.modifyType = (String) value;
        } else if(eventType.equals(Event.MODIFY_LOCATION)){
            this.modifyLocation = (String) value;
        }    
    }

}
