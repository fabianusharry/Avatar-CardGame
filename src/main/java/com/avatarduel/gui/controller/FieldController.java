package com.avatarduel.gui.controller;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.field.CardField;
import com.avatarduel.model.field.CharacterField;
import com.avatarduel.model.field.SkillField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FieldController implements Initializable{
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
    @FXML private List<Pane> CharacterFields;
    @FXML private List<Pane> SkillFields;
    public Player player;
    private EventManager events;


    public FieldController(Player player) throws Exception{
        this.player = player;
        events = new EventManager(Event.CARD_PLACED_TO_FIELD);
        events.subscribe(Event.CARD_PLACED_TO_FIELD,GameController.getInstance());
    }
    
    public Card getCardAt(String args,int index){
        if(args.equals("Skill")){
            return player.field.getSkillField().getCard(index);
        }
        else{
            return player.field.getCharacterField().getCard(index);
        }
    }
    
    public CardField getCardField(){
        return this.player.field;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            CharacterField c = player.field.getCharacterField();
            SkillField s = player.field.getSkillField();
            for(int i=0;i<6;i++){
                if(c.getCard(i)!=null){
                    CharacterFields.get(i).getChildren().add(new MiniCardLoader(player.field.getCharacterField().getCard(i)).getPane());
                }
                if(s.getCard(i)!=null){
                    SkillFields.get(i).getChildren().add(new MiniCardLoader(player.field.getSkillField().getCard(i)).getPane());
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void placeCard(javafx.event.Event evt) throws Exception{
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        Card placing = GameController.getInstance().getCardPlacing();
        if(evt.getSource().toString().contains("Character")){
            //Berarti yang bisa dimasukkan adalah kartu KARAKTER
            if(placing instanceof com.avatarduel.model.card.Character){
                if(player.field.getCharacterField().getCard(Integer.parseInt(id))==null){
                    CharacterFields.get(Integer.parseInt(id)-1).getChildren().add(new MiniCardLoader(placing).getPane());
                    events.notify(Event.CARD_PLACED_TO_FIELD,player.getName());
                }
            }
        }
        else{
            if(placing instanceof com.avatarduel.model.card.Skill){
                if(player.field.getSkillField().getCard(Integer.parseInt(id))==null){
                    SkillFields.get(Integer.parseInt(id)-1).getChildren().add(new MiniCardLoader(placing).getPane());
                    events.notify(Event.CARD_PLACED_TO_FIELD,player.getName());
                }
            }
        }
    }

    void enable(boolean value) {
        if (value) {
            for(int i = 0; i < 6 ; i++){
                CharacterFields.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    try {
                        placeCard(e);
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                });
                SkillFields.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
                    try{

                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                });
            }
        } else {
            for (int i = 0; i < 6; i++) {
                CharacterFields.get(i).setOnMouseClicked(null);
                SkillFields.get(i).setOnMouseClicked(null);
            }
        }
    }
}
