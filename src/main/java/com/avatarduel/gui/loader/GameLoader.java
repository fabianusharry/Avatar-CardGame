package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.CardController;
import com.avatarduel.gui.controller.GameController;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import com.avatarduel.util.Constants;

public class GameLoader {
    private static GameLoader instance = null; //Singleton attribute

    Pane game;

    public static GameLoader getInstance() throws Exception {
        if (instance == null) {
            instance = new GameLoader();
        }
        return instance;
    }

    public static Boolean isInstantiated() {
        return instance != null;
    }

    private GameLoader() throws Exception {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.gameFxml));
        loader.setController(GameController.getInstance());
        // make stage
        this.game = loader.load();

    }

    public Pane getPane() {
        return game;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(game, 1360, 768);
        stage.setTitle(Constants.gameTitle);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);
        stage.getIcons().add(new Image(AvatarDuel.class.getResource("momo.png").toString()));
        stage.setScene(scene);
        stage.show();
    }
}
