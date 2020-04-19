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
 * PowerLoader in GUI
 * Contains pane for show Player's Power
 */
public class PowerLoader implements Loader {
    Pane power;

    /**
     * Creates a new PowerLoader for player
     * @param player player
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    public PowerLoader(Player player) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.powerFxml));
        PowerController controller = new PowerController(player.getPowerNow(), player.getMaxPower());
        loader.setController(controller);
        this.power = loader.load();

    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return power;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(power, 99, 310);
        stage.setScene(scene);
        stage.show();
    }
}
