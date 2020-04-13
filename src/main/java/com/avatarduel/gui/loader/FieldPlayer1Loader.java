/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.FieldPlayer1Controller;
import com.avatarduel.model.field.CardField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author personate
 */
public class FieldPlayer1Loader implements Loader{
    Pane field;

    public FieldPlayer1Loader(CardField cards) throws Exception {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/FieldPlayer1.fxml"));

        FieldPlayer1Controller controller = new FieldPlayer1Controller(cards);
        loader.setController(controller);
        // make stage
        this.field = loader.load();

    }

    public Pane getPane() {
        return field;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(field, 717, 180);
        stage.setScene(scene);
        stage.setTitle("Avatar Card Game");
        stage.show();
    }
    
}
