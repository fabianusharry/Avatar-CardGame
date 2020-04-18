package com.avatarduel.gui.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.Constants;
import com.avatarduel.model.card.Skill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML private Text attr1;
    @FXML private Text attr2;
    @FXML private Text attr3;
    @FXML private Text attr4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        description.setText(card.getDescription());
        description.setEditable(false);
        description.setStyle("-fx-opacity: 0.8;");
        name.setText(card.getName());
        element.setText(card.getElement().toString());
        cardImage.setImage(new Image(getClass().getSuperclass().getResource(card.getImgPath()).toString()));
        setAttribute();
    }

    public void setAttribute() {
        String imgPath = null;
        if (card instanceof com.avatarduel.model.card.Character) {
            attr1.setText("ATT: " + card.getAttribute(Attribute.ATTACK));
            attr2.setText("DEF: " + card.getAttribute(Attribute.DEFENSE));
            attr3.setText("POW: " + card.getAttribute(Attribute.POWER));
            imgPath = Constants.characterCardTemplateImage;
            attr4.setText("Skill Attached : None");
        } else if (card instanceof com.avatarduel.model.card.Skill) {
            attr1.setText("SKILL");
            if (card instanceof com.avatarduel.model.card.effect.Aura) {
                attr2.setText("AURA");
                attr4.setText("ATT: " + card.getAttribute(Attribute.ATTACK) + ", " + "DEF: " + card.getAttribute(Attribute.DEFENSE));
            } else if (card instanceof com.avatarduel.model.card.effect.Destroy) {
                attr2.setText("DESTROY");
            } else {
                attr2.setText("PWR UP");
            }
            attr3.setText("POW: " + card.getAttribute(Attribute.POWER));
            imgPath = Constants.skillCardTemplateImage;
        } else if (card instanceof com.avatarduel.model.card.Land) {
            attr3.setText("LAND");
            imgPath = Constants.landCardTemplateImage;
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

    public void addSkillAttached(List<Skill> skillAttached) {
        StringBuilder value = new StringBuilder("Skill Attached: ");
        if (skillAttached.size() == 0) {
            value.append("None");
        } else {
            String skills = skillAttached.stream().map(Card::getName).collect(Collectors.joining(", "));
            value.append(skills);
        }
        attr4.setText(String.valueOf(value));
    }
}