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
    private void startGame() throws Exception {
        nameOfPlayer1 = playerName1.getText();
        nameOfPlayer2 = playerName2.getText();
        MainMenuLoader.getInstance().closeStage();
        GameLoader.getInstance().render();
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
        playerName1.setText("Masukkan nama player 1");
        playerName2.setText("Masukkan nama player 2");
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
