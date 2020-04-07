package com.avatarduel.gui.Loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.Controller.CardController;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CardLoader implements Loader {
    Stage card;

    public CardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/card.fxml"));
        CardController controller = new CardController(card);
        loader.setController(controller);
        // make stage
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane, 480, 640);
        this.card = new Stage();
        this.card.setScene(scene);
    }

    public void render() {
        card.show();
    }
}
