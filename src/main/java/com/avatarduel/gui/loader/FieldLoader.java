package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.FieldController;
import com.avatarduel.model.field.CardField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.avatarduel.util.Constants;

public class FieldLoader implements Loader{
    Pane field;

    public FieldLoader(CardField cards, int playerNumber) throws Exception {
        // init loader
        FXMLLoader loader;
        if (playerNumber == 1) {
            loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.fieldPlayer1Fxml));
        } else {
            loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.fieldPlayer2Fxml));
        }

        FieldController controller = new FieldController(cards);
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
