package com.avatarduel.gui.controller;
import com.avatarduel.model.field.CardField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class FieldPlayer2Controller implements Initializable{
    @FXML private Pane Character1;
    @FXML private Pane Character2;
    @FXML private Pane Character3;
    @FXML private Pane Character4;
    @FXML private Pane Character5;
    @FXML private Pane Character6;
    @FXML private Pane Skill1;
    @FXML private Pane Skill2;
    @FXML private Pane Skill3;
    @FXML private Pane Skill4;
    @FXML private Pane Skill5;
    @FXML private Pane Skill6;
    public CardField cards;
    HashMap<Pane,Integer> map = new HashMap<>();
    public List<Pane> CharacterFields = new ArrayList<>(Arrays.asList(Character1,Character2,Character3,Character4,Character5,Character6));
    public List<Pane> SkillFields = new ArrayList<>(Arrays.asList(Skill1,Skill2,Skill3,Skill4,Skill5,Skill6));
    public FieldPlayer2Controller(){
        this.cards = new CardField();
        
    }
    
    
    public CardField getCardField(){
        return this.cards;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }
}
//    public void initialize(URL location, ResourceBundle resources) {
//        airNow.setText(String.valueOf(powerNow.get(Element.AIR)));
//        airMax.setText(String.valueOf(powerMax.get(Element.AIR)));
//        earthNow.setText(String.valueOf(powerNow.get(Element.EARTH)));
//        earthMax.setText(String.valueOf(powerMax.get(Element.EARTH)));
//        fireNow.setText(String.valueOf(powerNow.get(Element.FIRE)));
//        fireMax.setText(String.valueOf(powerMax.get(Element.FIRE)));
//        waterNow.setText(String.valueOf(powerNow.get(Element.WATER)));
//        waterMax.setText(String.valueOf(powerMax.get(Element.WATER)));
//    }

////    Power powerNow;
////    Power powerMax;
////    public PowerController(Power powerNow, Power powerMax ) {
////        this.powerNow = powerNow;
////        this.powerMax = powerMax;
////    }



//    
//}
