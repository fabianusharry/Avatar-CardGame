package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MiniCardController implements Initializable {
    private Card card;

    public MiniCardController(Card card) {
        this.card = card;
    }

    @FXML private ImageView template;
    @FXML private ImageView cardImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (card != null) {
            cardImage.setImage(new Image(AvatarDuel.class.getResource(card.getImgPath()).toString()));
            if (card instanceof com.avatarduel.model.card.Character) {
                template.setImage(new Image(AvatarDuel.class.getResource("card/image/template/characterCard.png").toString()));
            } else if (card instanceof com.avatarduel.model.card.Land) {
                template.setImage(new Image(AvatarDuel.class.getResource("card/image/template/landCard.png").toString()));
            } else {
                template.setImage(new Image(AvatarDuel.class.getResource("card/image/template/skillCard.png").toString()));
            }
        }
    }
}
