package com.avatarduel.gui.controller;

import java.io.IOException;
import java.lang.Exception;
import com.avatarduel.AvatarDuel;
import com.avatarduel.gui.loader.GameLoader;
import com.avatarduel.gui.loader.MainMenuLoader;
import com.avatarduel.exceptions.*;
import com.avatarduel.gui.loader.MessageBoxLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        // throw exception if player name empty
        try {
            if (playerName1.getText().equals(TEMPLATE) || playerName1.getText().equals("")) {
                playerName1.requestFocus();
                throw new PlayerNameEmptyException();
            }
            if (playerName2.getText().equals(TEMPLATE) || playerName2.getText().equals("")) {
            playerName2.requestFocus();
                throw new PlayerNameEmptyException();
            }

            // throw if player' name same
            if (playerName1.getText().equals(playerName2.getText())) {
            playerName1.requestFocus();
                throw new PlayerNameSameException();
            }

            nameOfPlayer1 = playerName1.getText();
            nameOfPlayer2 = playerName2.getText();
            MainMenuLoader.getInstance().closeStage();
            GameLoader.getInstance().render();

        } catch (Exception ex) {
            new MessageBoxLoader(ex.getMessage()).render();
        }
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
