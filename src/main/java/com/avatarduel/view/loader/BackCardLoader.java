package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

/**
 * BackCardLoader in GUI
 * Contains pane for showing back side of a card
 */
public class BackCardLoader {
    Pane card;

    /**
     * Creates a new BackCardLoader
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    public BackCardLoader() throws IOException {
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.backCardFxml));
        this.card = loader.load();
    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return card;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 58, 85);
        stage.setScene(scene);
        stage.show();
    }
}
