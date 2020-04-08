package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.CardController;
import com.avatarduel.gui.controller.GameController;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameLoader {
    Pane game;

    public GameLoader(AvatarDuel game) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/game.fxml"));
        GameController controller = new GameController(game);
        loader.setController(controller);
        // make stage
        this.game = loader.load();

    }

    public Pane getPane() {
        return game;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(game, 1360, 768);
        stage.setScene(scene);
        stage.show();
    }
}
