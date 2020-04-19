package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.FieldController;
import com.avatarduel.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.avatarduel.util.Constants;

import java.io.IOException;

/**
 * FieldLoader in GUI
 * Contains pane for showing player's field
 */
public class FieldLoader implements Loader{
    Pane field;
    FieldController controller;

    /**
     * Load fxml and set a controller
     *
     * @param player is player that own the field
     * @param playerNumber 1 if it's player 1 and 2 if it's player 2
     * @throws Exception exception when load fxml file and controller
     *         for example: file not found error
     */
    public FieldLoader(Player player, int playerNumber) throws Exception {
        FXMLLoader loader;
        if (playerNumber == 1) {
            loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.fieldPlayer1Fxml));
        } else {
            loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.fieldPlayer2Fxml));
        }
        controller = new FieldController(player);
        loader.setController(controller);
        this.field = loader.load();
    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return field;
    }

    /**
     * @return controller of this object
     */
    public FieldController getController(){ return controller; }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(field, 717, 180);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.show();
    }
    
}
