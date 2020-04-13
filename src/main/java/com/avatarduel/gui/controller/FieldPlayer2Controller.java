package com.avatarduel.gui.controller;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
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
//    HashMap<Pane,Integer> map = new HashMap<>();
    HashMap<Integer,Pane> map = new HashMap<>();
    public List<Pane> CharacterFields = new ArrayList<>(Arrays.asList(Character1,Character2,Character3,Character4,Character5,Character6));
    public List<Pane> SkillFields = new ArrayList<>(Arrays.asList(Skill1,Skill2,Skill3,Skill4,Skill5,Skill6));
    public FieldPlayer2Controller(CardField cards){
        this.cards = cards;
        this.map.put(0,Character1);
        this.map.put(1,Character2);
        this.map.put(2,Character3);
        this.map.put(3,Character4);
        this.map.put(4,Character5);
        this.map.put(5,Character6);
        this.map.put(6,Skill1);
        this.map.put(7,Skill2);
        this.map.put(8,Skill3);
        this.map.put(9,Skill4);
        this.map.put(10,Skill5);
        this.map.put(11,Skill6);
    }
    
    public Card getCardAt(String args,int index){
        if(args.equals("Skill")){
            return cards.getSkillField().getCard(index);
        }
        else{
            return cards.getCharacterField().getCard(index);
        }
    }
    
    public CardField getCardField(){
        return this.cards;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            CharacterField c = cards.getCharacterField();
            SkillField s = cards.getSkillField(); 
            for(int i = 0;i<6;i++){
                Pane p = map.get(i);
                p.getChildren().add(new MiniCardLoader(c.getCard(i)).getPane());
            }
            for(int i = 6;i<12;i++){
                Pane p = map.get(i);
                p.getChildren().add(new MiniCardLoader(s.getCard(i%6)).getPane());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void placeCard(Card c,int index) throws IOException{
        if(c instanceof Skill){
            if(getCardAt("Skill",index)!=null){
                cards.getSkillField().placeCard(index, c);
                Pane p = map.get(index+6);
                p.getChildren().add(new MiniCardLoader(c).getPane());
            }
        }
        else if(c instanceof com.avatarduel.model.card.Character){
            if(getCardAt("Character",index)!=null){
                cards.getCharacterField().placeCard(index,c);
                Pane p = map.get(index);
                p.getChildren().add(new MiniCardLoader(c).getPane());
            }
        }
    }
    
}
