
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
     * Call constructor by exception
     *
     * @param err exception that had benn catched
     * @throws IOException exception from constrcutor
     */
    public MessageBoxLoader(Exception err) throws IOException {
        this(err.getMessage());
    }

    /**
     * Load fxml and set a controller
     *
     * @param Message is message that will be shown
     * @throws IOException exception when load fxml file and controller
     *         for example: file not found error
     */
    public MessageBoxLoader(String Message) throws IOException {
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.messageBoxFxml));
        MessageBoxController controller = new MessageBoxController(Message);
        loader.setController(controller);
        MessageBox = loader.load();
    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() { return  MessageBox; }

    /**
     * create new stage and scene
     * show in new window
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
