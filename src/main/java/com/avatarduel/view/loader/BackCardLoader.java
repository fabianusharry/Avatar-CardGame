package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

public class BackCardLoader {
    Pane card;

    public BackCardLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.backCardFxml));
        // make stage
        this.card = loader.load();
    }

    public Pane getPane() {
        return card;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 58, 85);
        stage.setScene(scene);
        stage.show();
    }
}
