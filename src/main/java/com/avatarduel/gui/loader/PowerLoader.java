package com.avatarduel.gui.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.controller.CardController;
import com.avatarduel.gui.controller.PowerController;
import com.avatarduel.model.Player;
import com.avatarduel.model.Power;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PowerLoader implements Loader {
    Pane power;

    public PowerLoader(Player player) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource("fxml/power.fxml"));
        PowerController controller = new PowerController(player.getPowerNow(), player.getMaxPower());
        loader.setController(controller);
        // make stage
        this.power = loader.load();

    }

    public Pane getPane() {
        return power;
    }

    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(power, 99, 310);
        stage.setScene(scene);
        stage.show();
    }
}
