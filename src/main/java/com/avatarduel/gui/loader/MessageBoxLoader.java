package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.MessageBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MessageBoxLoader implements Loader {
    private Pane MessageBox;

    public MessageBoxLoader(String Message) {
        try {
            FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/MessageBox.fxml"));
            MessageBoxController controller = new MessageBoxController(Message);
            loader.setController(controller);
            MessageBox = loader.load();

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Override
    public Pane getPane() { return  MessageBox; }

    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(MessageBox);
        scene.getStylesheets().add(AvatarDuel.class.getResource("fxml/css/MessageBox.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Avatar Card Game");
        stage.show();
    }
}
