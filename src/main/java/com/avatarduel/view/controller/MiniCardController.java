package com.avatarduel.view.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A Controller for MiniCard Pane in Hand
 */
public class MiniCardController implements Initializable {
    private Card card;

    /**
     * Creates a new MiniCardController
     * @param card card
     */
    public MiniCardController(Card card) {
        this.card = card;
    }

    @FXML private ImageView template;
    @FXML private ImageView cardImage;


    /**
     * Initialize attribute in MiniCard
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (card != null) {
            cardImage.setImage(new Image(AvatarDuel.class.getResource(card.getImgPath()).toString()));
            if (card instanceof com.avatarduel.model.card.Character) {
                template.setImage(new Image(AvatarDuel.class.getResource(Constants.characterCardTemplateImage).toString()));
            } else if (card instanceof com.avatarduel.model.card.Land) {
                template.setImage(new Image(AvatarDuel.class.getResource(Constants.landCardTemplateImage).toString()));
            } else {
                template.setImage(new Image(AvatarDuel.class.getResource(Constants.skillCardTemplateImage).toString()));
            }
        }
    }
}
