
package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.MessageBoxController;
import com.avatarduel.util.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MessageBoxLoader in GUI
 * Contains pane for showing error in Message Box
 */
public class MessageBoxLoader implements Loader {
    private Pane MessageBox;

    /**
     * Creates a new MiniCardLoader for Exception
     * @param err exception error
     */
    public MessageBoxLoader(Exception err) {
        this(err.getMessage());
    }

    /**
     * Creates a new MessageBoxLoader for message
     * @param Message message
     */
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

    /**
     * Get pane
     * @return messageBox
     */
    @Override
    public Pane getPane() { return  MessageBox; }

    /**
     * Render pane to new stage
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(MessageBox);
        scene.getStylesheets().add(AvatarDuel.class.getResource(Constants.messageBoxCss).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.setResizable(false);
        stage.getIcons().add(new Image(AvatarDuel.class.getResource("momo.png").toString()));
        stage.show();
    }
}
