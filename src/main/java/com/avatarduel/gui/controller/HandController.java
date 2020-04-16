package com.avatarduel.gui.controller;

import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.BackCardLoader;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
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
        events = new EventManager(Event.CHANGE_CARD_VIEW, Event.UPDATE_POWER, Event.GOT_CARD, Event.DISABLE_LAND_CARDS, Event.PASS_CARD);
        events.subscribe(Event.CHANGE_CARD_VIEW, GameController.getInstance());
        events.subscribe(Event.UPDATE_POWER, GameController.getInstance());
        events.subscribe(Event.GOT_CARD,GameController.getInstance());
        events.subscribe(Event.PASS_CARD, GameController.getInstance());
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

    @FXML
    public Card getCard(javafx.event.Event evt) throws Exception {
        Card takenCard = null;
        if (enableClick) {
            boolean canTake = true;
            System.out.println("click");
            String id = evt.getSource().toString().replaceAll("[^0-9]",""); // ambil integernya aja
            if ((player.getHandCards().peek(Integer.parseInt(id)) instanceof com.avatarduel.model.card.Land && disableLand)) {
                canTake = false;
            }
            if (canTake) {
                takenCard = player.takeCard(Integer.parseInt(id));
                if (takenCard != null) {
                    reloadCardsPane();
                    events.notify(Event.UPDATE_POWER, player.getName());
                    events.notify(Event.PASS_CARD,takenCard);
                    if (takenCard instanceof com.avatarduel.model.card.Land) {
                        disableLand = true;
                    } else {
                        events.notify(Event.GOT_CARD,player.getName());
                    }
                } //ELSE KASIH NOTIF ERROR
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
