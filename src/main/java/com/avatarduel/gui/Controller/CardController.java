package com.avatarduel.gui.Controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.Card.Attribute;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController extends AvatarDuel implements Initializable {

    private Card card;

    public CardController(Card card) {
        this.card = card;
    }

    @FXML private Pane cardPane;
    @FXML private ImageView cardImage;
    @FXML private TextArea description;
    @FXML private Text name;
    @FXML private Text element;
    @FXML private Text attack;
    @FXML private Text defense;
    @FXML private Text power;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        description.setText(card.getDescription());
        description.setVisible(true);
        description.setDisable(true);
        name.setText(card.getName());
        element.setText(card.getElement().toString());
        cardImage.setImage(new Image(getClass().getSuperclass().getResource(card.getImgPath()).toString()));
        setAttribute();
    }

    public void setAttribute() {
        String imgPath = null;
        if (card instanceof com.avatarduel.model.Card.Character) {
            attack.setText("ATT: " + card.getAttribute(Attribute.ATTACK));
            defense.setText("DEF: " + card.getAttribute(Attribute.DEFENSE));
            power.setText("POW: " + card.getAttribute(Attribute.POWER));
            imgPath = "card/image/template/characterCard.png";
        } else if (card instanceof com.avatarduel.model.Card.Skill) {
            power.setText("POW: " + card.getAttribute(Attribute.POWER));
            imgPath = "card/image/template/skillCard.png";
        } else if (card instanceof com.avatarduel.model.Card.Land) {
            imgPath = "card/image/template/landCard.png";
        }
        setBackground(imgPath);
    }

    public void setBackground(String imgPath) {
        Image img = new Image(AvatarDuel.class.getResource(imgPath).toString(), 480, 640, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100,100,true,true,false,true));

        Background background = new Background(backgroundImage);
        cardPane.setBackground(background);
    }
}
