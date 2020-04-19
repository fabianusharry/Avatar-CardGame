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
public class GameLoader implements Loader {
    private static GameLoader instance = null; //Singleton attribute

    Stage stage;
    Pane game;

    /**
     *
     * @return instance of this object if its not null, else create a new one and return it
     * @throws Exception exception when create new loader
     *         for example: file not found error
     */
    public static GameLoader getInstance() throws Exception {
        if (instance == null) {
            instance = new GameLoader();
        }
        return instance;
    }

    /**
     * @return true if instance is not null, else false
     */
    public static Boolean isInstantiated() {
        return instance != null;
    }

    /**
     * Load fxml and set a controller
     *
     * @throws Exception exception when load fxml file and controller
     *         for example: file not found error
     */
    private GameLoader() throws Exception {
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.gameFxml));
        loader.setController(GameController.getInstance());
        this.game = loader.load();
    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return game;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
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
     * Exit the game
     */
    public void exit() {
        game.setDisable(true);
    }
}
