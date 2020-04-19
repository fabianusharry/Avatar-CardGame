package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.CardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;
import java.util.List;

/**
 * CardLoader in GUI
 * Contains pane for showing BIG card in GUI
 */
public class CardLoader implements Loader {
    Pane card;
    CardController controller;

    /**
     * Creates a new CardLoader
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    public CardLoader(Card card) throws IOException {
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.cardFxml));
        controller = new CardController(card);
        loader.setController(controller);
        this.card = loader.load();

    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return card;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set skill Attached in Skill Card Pane
     */
    public void setSkillAttached(List<Skill> skillAttached) {
        controller.addSkillAttached(skillAttached);
    }
}
