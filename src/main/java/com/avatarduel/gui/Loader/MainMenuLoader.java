package com.avatarduel.gui.Loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.Controller.MainMenuController;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuLoader implements Loader {
    Pane mainMenu;

    public MainMenuLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/loading.fxml"));

        MainMenuController controller = new MainMenuController();
        loader.setController(controller);
        // make stage
        AnchorPane pane = loader.load();
        
    }

    public Pane getPane() {
        return mainMenu;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(mainMenu, 480, 640);
        stage.setScene(scene);
        stage.show();
    }
}
