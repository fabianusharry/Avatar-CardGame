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

    /**
     * @param player owner of the hand cards
     * @throws Exception exception when load fxml file and controller
     *         for example: file not found error
     */
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
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return mainMenu;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(mainMenu, 648, 85);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.show();
    }

    /**
     *
     * @return controller of this object
     */
    public HandController getController() {
        return controller;
    }
}
