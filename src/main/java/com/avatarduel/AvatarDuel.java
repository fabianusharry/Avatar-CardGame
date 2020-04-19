package com.avatarduel;

import com.avatarduel.view.loader.MainMenuLoader;
import com.avatarduel.model.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainMenuLoader mainMenu = MainMenuLoader.getInstance();
        mainMenu.render(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}