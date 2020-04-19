package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

/**
 * MiniCardLoader used to create new BackCard object on screen
 *
 */
public class BackCardLoader implements Loader {
    Pane card;

    /**
     * @throws IOException exception when load fxml file
     *         for example: file not found error
     */
    public BackCardLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.backCardFxml));
        // make stage
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
