package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.controller.MainMenuController;
import com.avatarduel.gui.loader.CardLoader;
import com.avatarduel.gui.loader.GameLoader;
import com.avatarduel.gui.loader.MainMenuLoader;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
    public boolean endGame;
    public Player P1;
    public Player P2;

    @Override
    public void start(Stage stage) throws Exception {
        endGame = false;

        MainMenuLoader mainMenu = MainMenuLoader.getInstance();
        mainMenu.render(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}