package com.avatarduel.gui.Controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController extends AvatarDuel implements Initializable {

    private Card card;

    public CardController(Card card) {
        this.card = card;
    }

    @FXML private ImageView cardImage;
    @FXML private TextArea description;
    @FXML private Text name;
    @FXML private Text element;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        description.setText(card.getDescription());
        description.setDisable(true);
        name.setText(card.getName());
        element.setText(card.getElement().toString());
        cardImage.setImage(new Image(getClass().getSuperclass().getResource(card.getImgPath()).toString()));
    }

}
