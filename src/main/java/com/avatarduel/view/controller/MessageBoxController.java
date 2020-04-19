package com.avatarduel.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for MessageBox
 */
public class MessageBoxController implements Initializable {

    @FXML private Label MessageView;
    public String Message;

    /**
     * Creates a new MessageBoxController
     * @param msg message
     */
    public MessageBoxController(String msg) { Message = msg; }

    public void setMessage(String message) {
        Message = message;
    }

    /**
     * Initialize MessageBox attributes
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MessageView.setText(Message);
    }
}
