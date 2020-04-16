package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.CardController;
import com.avatarduel.gui.controller.MiniCardController;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

public class MiniCardLoader implements Loader {
    Pane card;

    public MiniCardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.miniCardFxml));
        MiniCardController controller = new MiniCardController(card);
        loader.setController(controller);
        // make stage
        this.card = loader.load();
    }

    public Pane getPane() {
        return card;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 58, 85);
        stage.setScene(scene);
        stage.show();
    }
}
