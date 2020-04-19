package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.avatarduel.util.Constants;

import java.io.IOException;

/**
 * GameLoader in GUI
 * Contains pane for show AvatarDuel Game
 */
public class GameLoader {
    private static GameLoader instance = null; //Singleton attribute

    Stage stage;
    Pane game;

    /**
     * Singleton design pattern implementation
     * @return instance
     * @throws IOException exception when fail to create GameLoader object
     */
    public static GameLoader getInstance() throws Exception {
        if (instance == null) {
            instance = new GameLoader();
        }
        return instance;
    }

    /**
     * Check if Game is instantiated (or started)
     * @return instance != null
     */
    public static Boolean isInstantiated() {
        return instance != null;
    }

    /**
     * Creates a new GameLoader
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    private GameLoader() throws Exception {
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.gameFxml));
        loader.setController(GameController.getInstance());
        this.game = loader.load();
    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return game;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        stage = new Stage();
        Scene scene = new Scene(game, 1348, 758);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(AvatarDuel.class.getResource("momo.png").toString()));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Disable tha Game stage
     */
    public void exit() {
        game.setDisable(true);
    }
}
