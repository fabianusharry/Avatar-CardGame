package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.HandController;
import com.avatarduel.gui.controller.MainMenuController;
import com.avatarduel.model.cards.HandCards;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HandLoader implements Loader {
    Pane mainMenu;

    public HandLoader(HandCards hand) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/hand.fxml"));

        HandController controller = new HandController(hand);
        loader.setController(controller);
        // make stage
        this.mainMenu = loader.load();

    }

    public Pane getPane() {
        return mainMenu;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(mainMenu, 648, 85);
        stage.setScene(scene);
        stage.setTitle("Avatar Card Game");
        stage.show();
    }
}
