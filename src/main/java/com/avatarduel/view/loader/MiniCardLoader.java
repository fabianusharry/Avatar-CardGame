package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.MiniCardController;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

/**
 * MiniCardLoader in GUI
 * Contains pane for show Mini card in player's hand
 */
public class MiniCardLoader implements Loader {
    Pane card;

    /**
     * Creates a new MiniCardLoader for card
     * @param card card
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    public MiniCardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.miniCardFxml));
        MiniCardController controller = new MiniCardController(card);
        loader.setController(controller);
        this.card = loader.load();
    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return card;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 58, 85);

        stage.setScene(scene);
        stage.show();
    }
}
