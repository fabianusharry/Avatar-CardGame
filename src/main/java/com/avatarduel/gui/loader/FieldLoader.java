package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.FieldController;
import com.avatarduel.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FieldLoader implements Loader{
    Pane field;
    FieldController controller;

    public FieldLoader(Player player, int playerNumber) throws Exception {
        // init loader
        FXMLLoader loader;
        if (playerNumber == 1) {
            loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/fieldPlayer1.fxml"));
        } else {
            loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/fieldPlayer2.fxml"));
        }

        controller = new FieldController(player);
        loader.setController(controller);
        // make stage
        this.field = loader.load();

    }

    public Pane getPane() {
        return field;
    }

    public FieldController getController(){ return controller; }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(field, 717, 180);
        stage.setScene(scene);
        stage.setTitle("Avatar Card Game");
        stage.show();
    }
    
}
