package com.avatarduel;

import com.avatarduel.view.loader.MainMenuLoader;
import com.avatarduel.model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of AvatarDuel game
 */
public class AvatarDuel extends Application {
    /**
     * Start AvatarDuel game
     * @param stage stage
     * @throws Exception exception when MainMenu cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        MainMenuLoader mainMenu = MainMenuLoader.getInstance();
        mainMenu.render(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}