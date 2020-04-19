package com.avatarduel.view.controller;

import com.avatarduel.exceptions.HandOperationException;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventManager;
import com.avatarduel.view.loader.BackCardLoader;
import com.avatarduel.view.loader.GameLoader;
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

public class HandController implements Initializable {
    private Player player;
    private EventManager events;
    private boolean viewEnabled;
    private boolean disableLand;
    private boolean enableClick;

    @FXML private List<Pane> cards;

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

    public void setViewEnabled(boolean viewEnabled) {
        this.viewEnabled = viewEnabled;
    }

    public void setDisableLand(boolean disableLand) { this.disableLand = disableLand; }
    
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
    
    @FXML
    public Card getCard(javafx.event.Event evt) throws Exception {
        Card takenCard = null;
        String errMessage = null;
        boolean canTake = true;
        try {
            if (enableClick) {
                String id = evt.getSource().toString().replaceAll("[^0-9]",""); // ambil integernya aja
                if ((player.getHandCards().peek(Integer.parseInt(id)) instanceof com.avatarduel.model.card.Land && disableLand)) {
                    canTake = false;
                    errMessage = "Land hanya dapat diambil 1x";
                    throw new HandOperationException(errMessage);
                }
                else if(player.getHandCards().peek(Integer.parseInt(id)) instanceof com.avatarduel.model.card.Skill){
                    canTake = canPlaceSkill();
                    errMessage = "Tidak ada kartu karakter";
                    throw new HandOperationException(errMessage);
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
                } else {
                    new MessageBoxLoader(new HandOperationException(errMessage)).render();
                }
            } else {
                throw new HandOperationException("Sedang dalam battle phase");
            }
        } catch (Exception e) {
            new MessageBoxLoader(e.getMessage()).render();
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

    @FXML
    public void showCard(javafx.event.Event evt) throws Exception {
        if (viewEnabled) {
            String id = evt.getSource().toString().replaceAll("[^0-9]","");
            events.notify(Event.CHANGE_CARD_VIEW, player.getHandCards().peek(Integer.parseInt(id)));
        }
    }

    public void flipCards() throws IOException {
        for (int i = 0; i < player.getHandCards().size(); i++) {
            cards.get(i).getChildren().clear();
            cards.get(i).getChildren().add(new BackCardLoader().getPane());
        }
        this.viewEnabled = false;
    }

    public void setEnableClick(boolean value) {
        enableClick = value;
    }
}
