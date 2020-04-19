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
 * MiniCardLoader used to create new MiniCard object in screen
 *
 */
public class MiniCardLoader implements Loader {
    Pane card;

    /**
     *
     * @param card to be made a view
     * @throws IOException exception when load fxml file
     *         for example: file not found error
     */
    public MiniCardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.miniCardFxml));
        MiniCardController controller = new MiniCardController(card);
        loader.setController(controller);
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
