package com.avatarduel.gui.controller;

import java.io.IOException;
import java.lang.Exception;
import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.loader.GameLoader;
import com.avatarduel.gui.loader.MainMenuLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends AvatarDuel implements Initializable {
    private static String nameOfPlayer1;
    private static String nameOfPlayer2;
    private static final String TEMPLATE = "Masukkan nama pemain";

    public MainMenuController(){
        nameOfPlayer1 = "";
        nameOfPlayer2 = "";
    }

    @FXML private Button startButton;
    @FXML private TextField playerName1;
    @FXML private TextField playerName2;

    @FXML
    private void startGame() throws Exception {
        if (!playerName1.getText().equals(TEMPLATE) && !playerName1.getText().equals("")) {
            nameOfPlayer1 = playerName1.getText();
        } else {
            playerName1.requestFocus();
            return;
        }
        if (!playerName2.getText().equals(TEMPLATE)&& !playerName2.getText().equals("")) {
            nameOfPlayer2 = playerName2.getText();
        } else {
            playerName2.requestFocus();
            return;
        }
        MainMenuLoader.getInstance().closeStage();
        GameLoader.getInstance().render();
    }

    @FXML
    public void buttonPressed(KeyEvent e) throws Exception {
        if (e.getCode().toString().equals("ENTER")) {
            startGame();
        }
    }

    public String getName(int player) throws Exception {
        if(!nameOfPlayer1.equals("") && !nameOfPlayer2.equals("")){
            if(player == 1){
                return nameOfPlayer1;
            }
            if(player == 2){
                return nameOfPlayer2;
            }
        }
        else{
            throw new Exception("Nama Player Belum Diinput");
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerName1.setText(TEMPLATE);
        playerName2.setText(TEMPLATE);
    }

    @FXML
    void fillP1Name() {
        playerName1.clear();
    }

    @FXML
    void fillP2Name() {
        playerName2.clear();
    }
}
