package com.avatarduel.gui.Loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.Controller.CardController;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CardLoader implements Loader {
    Pane card;

    public CardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/card.fxml"));
        CardController controller = new CardController(card);
        loader.setController(controller);
        // make stage
        this.card = loader.load();

    }

    public Pane getPane() {
        return card;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 480, 640);
        stage.setScene(scene);
        stage.show();
    }
}
