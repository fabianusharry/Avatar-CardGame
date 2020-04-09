package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenuLoader implements Loader {
    Pane mainMenu;

    public MainMenuLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/menu.fxml"));

        MainMenuController controller = new MainMenuController();
        loader.setController(controller);
        // make stage
        this.mainMenu = loader.load();
        
    }

    public Pane getPane() {
        return mainMenu;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle("Avatar Card Game");
        stage.setResizable(false);
        stage.show();
    }
}
