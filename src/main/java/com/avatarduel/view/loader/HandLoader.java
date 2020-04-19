package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.HandController;
import com.avatarduel.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.avatarduel.util.Constants;

/**
 * HandLoader in GUI
 * Contains pane for showing HandCards
 */
public class HandLoader implements Loader {
    Pane mainMenu;
    HandController controller;

    public HandLoader(Player player) throws Exception {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.handFxml));
        controller = new HandController(player);
        loader.setController(controller);
        // make stage
        this.mainMenu = loader.load();
//        controller.flipCards();

    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return mainMenu;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(mainMenu, 648, 85);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.show();
    }

    public HandController getController() {
        return controller;
    }
}
