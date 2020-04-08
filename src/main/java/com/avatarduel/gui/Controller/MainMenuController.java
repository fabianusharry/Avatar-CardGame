package com.avatarduel.gui.Controller;

import java.lang.Exception;
import com.avatarduel.AvatarDuel;
import com.avatarduel.model.Card.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends AvatarDuel implements Initializable {
    private static String nameOfPlayer1;
    private static String nameOfPlayer2;

    public MainMenuController(){
        nameOfPlayer1 = "";
        nameOfPlayer2 = "";
    }

    @FXML private Button startButton;
    @FXML private TextField playerName1;
    @FXML private TextField playerName2;
    @FXML
    private void handleStartButtonPress(ActionEvent event)
    {
        nameOfPlayer1 = playerName1.getText();
        nameOfPlayer2 = playerName2.getText();
        System.out.println(nameOfPlayer1);
        System.out.println(nameOfPlayer2);
        playerName1.setText("");
        playerName2.setText("");   
    }

    public static String getName(int player) throws Exception{
        if(!nameOfPlayer1.equals("") && !nameOfPlayer2.equals("")){
            if(player == 1){
                return nameOfPlayer1;
            }
            if(player == 2){
                return nameOfPlayer2;
            }
        }
        else{
            throw new Exception("Nama Player Belum diinput");
        }
        return "-1";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setOnAction(this::handleStartButtonPress);
        playerName1.setText("Masukkan nama player 1");
        playerName2.setText("Masukkan nama player 2");
        // description.setDisable(true);
        // name.setText(card.getName());
        // element.setText(card.getElement().toString());
        // cardImage.setImage(new Image(getClass().getSuperclass().getResource(card.getImgPath()).toString()));
    }

}
