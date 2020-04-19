package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.PowerController;
import com.avatarduel.model.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

/**
 * Used to create power object on the screen
 */
public class PowerLoader implements Loader {
    Pane power;

    /**
     *
     * @param player player that own the power
     * @throws IOException exception when load fxml file and controller
     *         for example: file not found error
     */
    public PowerLoader(Player player) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.powerFxml));
        PowerController controller = new PowerController(player.getPowerNow(), player.getMaxPower());
        loader.setController(controller);
        // make stage
        this.power = loader.load();

    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return power;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(power, 99, 310);
        stage.setScene(scene);
        stage.show();
    }
}
