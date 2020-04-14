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
    @FXML private List<Pane> CharacterFields;
    @FXML private List<Pane> SkillFields;
    public CardField cards;


    public FieldPlayer2Controller(CardField cards) throws Exception{
        this.cards = cards;
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
        try {
            CharacterField c = cards.getCharacterField();
            SkillField s = cards.getSkillField();
            for(int i=0;i<6;i++){
                if(c.getCard(i)!=null){
                    CharacterFields.get(i).getChildren().add(new MiniCardLoader(cards.getCharacterField().getCard(i)).getPane());
                }
                if(s.getCard(i)!=null){
                    SkillFields.get(i).getChildren().add(new MiniCardLoader(cards.getSkillField().getCard(i)).getPane());
                }
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
                SkillFields.get(index).getChildren().add(new MiniCardLoader(c).getPane());
            }
        }
        else{
            if(getCardAt("Character",index)!=null){
                cards.getCharacterField().placeCard(index,c);
                CharacterFields.get(index).getChildren().add(new MiniCardLoader(c).getPane());
            }
        }
    }
}
