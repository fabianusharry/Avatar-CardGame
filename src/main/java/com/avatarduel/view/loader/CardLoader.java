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
 * MiniCardLoader used to create new Card object in screen
 *
 */
public class CardLoader implements Loader {
    Pane card;
    CardController controller;

    /**
     * @param card to be made a view
     * @throws IOException exception when load fxml file
     *         for example: file not found error
     */
    public CardLoader(Card card) throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.cardFxml));
        controller = new CardController(card);
        loader.setController(controller);
        // make stage
        this.card = loader.load();

    }

    /**
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return card;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        Stage stage = new Stage();
        Scene scene = new Scene(card, 480, 640);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * attach skills to card
     * @param skillAttached to be attached
     */
    public void setSkillAttached(List<Skill> skillAttached) {
        controller.addSkillAttached(skillAttached);
    }
}
