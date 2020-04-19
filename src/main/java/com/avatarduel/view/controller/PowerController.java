package com.avatarduel.view.controller;

import com.avatarduel.model.Power;
import com.avatarduel.model.card.Element;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A Controller for Power Pane GUI
 * Contains powerNow and powerMax
 */
public class PowerController implements Initializable {
    Power powerNow;
    Power powerMax;

    /**
     * Creates a new controller
     * @param powerNow powerNow
     * @param powerMax powerMax
     */
    public PowerController(Power powerNow, Power powerMax ) {
        this.powerNow = powerNow;
        this.powerMax = powerMax;
    }

    @FXML private Text airNow;
    @FXML private Text airMax;
    @FXML private Text earthNow;
    @FXML private Text earthMax;
    @FXML private Text fireNow;
    @FXML private Text fireMax;
    @FXML private Text waterNow;
    @FXML private Text waterMax;
    @FXML private Text energyNow;
    @FXML private Text energyMax;

    /**
     * Initialize text in pane
     * @param location location
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        airNow.setText(String.valueOf(powerNow.get(Element.AIR)));
        airMax.setText(String.valueOf(powerMax.get(Element.AIR)));
        earthNow.setText(String.valueOf(powerNow.get(Element.EARTH)));
        earthMax.setText(String.valueOf(powerMax.get(Element.EARTH)));
        fireNow.setText(String.valueOf(powerNow.get(Element.FIRE)));
        fireMax.setText(String.valueOf(powerMax.get(Element.FIRE)));
        waterNow.setText(String.valueOf(powerNow.get(Element.WATER)));
        waterMax.setText(String.valueOf(powerMax.get(Element.WATER)));
        energyNow.setText(String.valueOf(powerNow.get(Element.ENERGY)));
        energyMax.setText(String.valueOf(powerMax.get(Element.ENERGY)));
    }
}
