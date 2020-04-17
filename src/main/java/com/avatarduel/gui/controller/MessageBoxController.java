package com.avatarduel.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoxController implements Initializable {

    @FXML private Label MessageView;
    public String Message;

    public MessageBoxController(String msg) { Message = msg; }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MessageView.setText(Message);
    }
}
