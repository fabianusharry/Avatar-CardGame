package com.avatarduel.view.controller;

import com.avatarduel.exceptions.hand.EmptyCharacterException;
import com.avatarduel.exceptions.hand.LandTakenException;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventManager;
import com.avatarduel.view.loader.BackCardLoader;
import com.avatarduel.view.loader.MessageBoxLoader;
import com.avatarduel.view.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * HandController is a controller for Hand pane in AvatarDuel game.
 * @author Tony Eko Yuwono 13518030, Arief Darmawan Tantriady 13518015
 */
public class HandController implements Initializable {
    private Player player;
    private EventManager events;
    /**
     * if set to true, HandController will update CardView in game
     */
    private boolean viewEnabled;
    /**
     * if set to true, HandController will disable Land card click
     */
    private boolean disableLand;
    /**
     * if set to true, HandController will enable click for Hand pane
     */
    private boolean enableClick;

    @FXML private List<Pane> cards;

    /**
     * Creates a new HandController for a player
     * @param player player
     * @throws Exception exception when GameController cannot be insantiated
     */
    public HandController(Player player) throws Exception {
        this.player = player;
        viewEnabled = true;
        disableLand = false;
        events = new EventManager(Event.CHANGE_CARD_VIEW, Event.UPDATE_POWER, Event.GOT_CARD, Event.PASS_CARD,Event.SKILL_PLACING);
        events.subscribe(Event.CHANGE_CARD_VIEW, GameController.getInstance());
        events.subscribe(Event.UPDATE_POWER, GameController.getInstance());
        events.subscribe(Event.GOT_CARD,GameController.getInstance());
        events.subscribe(Event.PASS_CARD, GameController.getInstance());
        events.subscribe(Event.SKILL_PLACING,GameController.getInstance());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            for (int i = 0; i < 11; i++) {
                cards.get(i).getChildren().add(new MiniCardLoader(player.getHandCards().peek(i)).getPane());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set viewEnabled to change CardView in game
     * @param viewEnabled viewEnabled
     */
    public void setViewEnabled(boolean viewEnabled) {
        this.viewEnabled = viewEnabled;
    }

    /**
     * Set disableLand to disable Land card click
     * @param disableLand disableLand
     */
    public void setDisableLand(boolean disableLand) { this.disableLand = disableLand; }

    /**
     * Checks if there is any Character card in field
     * @return boolean that true if there is any Character card in field
     * @throws Exception exception when GameController cannot be insantiated
     */
    public boolean canPlaceSkill() throws Exception{
        GameController g = GameController.getInstance();
        for(int i=0;i<6;i++){
            if(g.getP1().field.getCharacterField().getCard(i)!=null){
                return true;
            }
            if(g.getP2().field.getCharacterField().getCard(i)!=null){
                return true;
            }
        }
        return false;
    }

    /**
     * Get card in hand
     * @param evt event source
     * @return taken card
         * @throws Exception exception when a card cannot be taken from hand
     */
    @FXML
    public Card getCard(javafx.event.Event evt) throws Exception {
        Card takenCard = null;
        boolean canTake = true;
        if (enableClick) {
            try {
                String id = evt.getSource().toString().replaceAll("[^0-9]",""); // ambil integernya aja
                if ((player.getHandCards().peek(Integer.parseInt(id)) instanceof com.avatarduel.model.card.Land && disableLand)) {
                    canTake = false;
                    throw new LandTakenException();
                } else if (player.getHandCards().peek(Integer.parseInt(id)) instanceof com.avatarduel.model.card.Skill){
                    canTake = canPlaceSkill();
                    if (!canTake) {
                        throw new EmptyCharacterException();
                    }
                }
                if (canTake) {
                    takenCard = player.takeCard(Integer.parseInt(id));
                    if (takenCard != null) {
                        reloadCardsPane();
                        events.notify(Event.UPDATE_POWER, player.getName());
                        events.notify(Event.PASS_CARD,takenCard);
                        if (takenCard instanceof com.avatarduel.model.card.Land) {
                            disableLand = true;
                        } else if(takenCard instanceof com.avatarduel.model.card.Skill){
                            events.notify(Event.SKILL_PLACING,(Skill) takenCard);
                            events.notify(Event.GOT_CARD,player.getName());
                        } else{
                            events.notify(Event.GOT_CARD,player.getName());
                        }
                    }
                }
            } catch (Exception e) {
                new MessageBoxLoader(e).render();
            }
        }
        return takenCard;
    }

    public void reloadCardsPane() throws IOException {
        for (int i = 0; i < 11; i++) {
            cards.get(i).getChildren().clear();
            if (i < player.getHandCards().size()) {
                cards.get(i).getChildren().add(new MiniCardLoader(player.getHandCards().peek(i)).getPane());
            }
        }
    }

    /**
     * Show card in Hand to CardView Pane
     * @param evt event source
     * @throws Exception exception when event cannot be notified
     */
    @FXML
    public void showCard(javafx.event.Event evt) throws Exception {
        if (viewEnabled) {
            String id = evt.getSource().toString().replaceAll("[^0-9]","");
            events.notify(Event.CHANGE_CARD_VIEW, player.getHandCards().peek(Integer.parseInt(id)));
        }
    }

    /**
     * Flip cards in hand to back side of the card
     * @throws IOException exception when BackCardLoader cannot load back side of the card
     */
    public void flipCards() throws IOException {
        for (int i = 0; i < player.getHandCards().size(); i++) {
            cards.get(i).getChildren().clear();
            cards.get(i).getChildren().add(new BackCardLoader().getPane());
        }
        this.viewEnabled = false;
    }

    /**
     * Set enableClick in HandController
     * @param value value
     */
    public void setEnableClick(boolean value) {
        enableClick = value;
    }
}
