package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.MessageBoxController;
import com.avatarduel.util.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MessageBoxLoader implements Loader {
    private Pane MessageBox;

    public MessageBoxLoader(Exception err) {
        this(err.getMessage());
    }

    public MessageBoxLoader(String Message) {
        try {
            FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.messageBoxFxml));
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
        scene.getStylesheets().add(AvatarDuel.class.getResource(Constants.messageBoxCss).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
    }
}
