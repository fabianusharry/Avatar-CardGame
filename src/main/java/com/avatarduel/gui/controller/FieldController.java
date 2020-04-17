package com.avatarduel.gui.controller;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;
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
import javafx.scene.transform.Rotate;

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
    private List<String> Enabled;
    private String onClickArgs;
    private Player player;
    private EventManager events;


    public FieldController(Player player) throws Exception{
        this.player = player;
        Enabled = new ArrayList<>();
        events = new EventManager(Event.CARD_PLACED,Event.CHANGE_CARD_VIEW,Event.PASS_SELECTED_CARD,Event.PASS_SELECTED_PANEID,Event.SELECTEDCARD);
        events.subscribe(Event.CARD_PLACED,GameController.getInstance());
        events.subscribe(Event.CHANGE_CARD_VIEW,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_CARD,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_PANEID,GameController.getInstance());
        events.subscribe(Event.SELECTEDCARD,GameController.getInstance());
        onClickArgs = "";
    }
    
    public void setOnClick(String args){
        //Ada 3 opsi, placeCard, useCard, AttackCard
        this.onClickArgs = args;
    }
    
    public Card getCardAt(String args,int index){
        if(args.equals("Skill")){
            return player.field.getSkillField().getCard(index);
        }
        else{
            return player.field.getCharacterField().getCard(index).getCharacter();
        }
    }
    
    public CardField getCardField(){
        return this.player.field;
    }
    
    public void setEnableClick(boolean b){
        if(b){
            enableAll();
        }
        else{
            disableAll();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            CharacterField c = player.field.getCharacterField();
            SkillField s = player.field.getSkillField();
            for(int i=0;i<6;i++){
                if(c.getCard(i)!=null){
                    CharacterFields.get(i).getChildren().add(new MiniCardLoader(player.field.getCharacterField().getCard(i).getCharacter()).getPane());
                }
                if(s.getCard(i)!=null){
                    SkillFields.get(i).getChildren().add(new MiniCardLoader(player.field.getSkillField().getCard(i)).getPane());
                }
            }
            reloadBorder();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void reloadFieldPane() throws Exception {
        for (int i = 0; i < 6; i++) {
            CharacterFields.get(i).getChildren().clear();
            if (player.field.getCharacterField().getCard(i)!=null) {
                CharacterFields.get(i).getChildren().add(new MiniCardLoader(player.field.getCharacterField().getCard(i).getCharacter()).getPane());
               
            }
            SkillFields.get(i).getChildren().clear();
            if(player.field.getSkillField().getCard(i)!=null){
                SkillFields.get(i).getChildren().add(new MiniCardLoader(player.field.getSkillField().getCard(i)).getPane());
                
            }
        }
    }
    
    public void reloadBorder(){
        for(int i = 0; i < 6;i++){
           CharacterFields.get(i).setStyle("-fx-border-color:black;");
           SkillFields.get(i).setStyle("-fx-border-color:black;");
        }
    }
    
    public void enableSkill(){
        for(int i=0; i < 6 ;i++){
            Enabled.add(SkillFields.get(i).getId());
        }
    }
    
    public void enableAll(){
        disableAll();
        enableCharacter();
        enableSkill();
    }
    
    public void enableCharacter(){
        for(int i=0; i < 6 ; i++){
            Enabled.add(CharacterFields.get(i).getId());
        }
    }
    
    public void enableSpecific(String paneId){
        System.out.println("Berhasil ditambah");
        Enabled.add(paneId);
    }
    
    public void disableSkill(){
        for(int i=0; i < 6 ;i++){
            Enabled.remove(SkillFields.get(i).getId());
        }
    }
    
    public void disableCharacter(){
        for(int i=0; i < 6 ; i++){
            Enabled.remove(CharacterFields.get(i).getId());
        }
    }
    
    public void disableSpecific(String paneId){
        System.out.println("Berhasil ditambah");
        Enabled.remove(paneId);
        System.out.println(Enabled);
    }
    
    public void disableAll(){
        Enabled = new ArrayList<>();
    }
    
    @FXML
    public void onClick(javafx.event.Event evt) throws Exception{
       System.out.println(Enabled);
       Pane p = (Pane) evt.getSource();
       int id;
        id = Integer.parseInt(p.getId().replaceAll("[^1-6]",""));
        if(Enabled.contains(p.getId())){
           switch (onClickArgs) {
               case "placeCard":
                placeCard(evt);
                break;
               case "selectCard":
                selectCard(evt);
                break;
               case "useCard":
                useCard(evt);
                break;
                //TODOLIST
               case "changeAttackMode":
                changeAttackMode(evt);
                break;
//               case "AttachSkill":
//                   AttachSkill(evt);
//                   break;
               default:
                   break;
           }
        }
    }
    
    @FXML
    public void onHover(javafx.event.Event evt) throws Exception{
        String id = evt.getSource().toString().replaceAll("[^0-9]","");
        if(evt.getSource().toString().contains("Character")){
            if(player.field.getCharacterField().getCard(Integer.parseInt(id)-1)!=null){
                events.notify(Event.CHANGE_CARD_VIEW, player.field.getCharacterField().getCard(Integer.parseInt(id)-1));
            }
        }
        else{
            if(player.field.getSkillField().getCard(Integer.parseInt(id)-1)!=null){
                events.notify(Event.CHANGE_CARD_VIEW,player.field.getSkillField().getCard(Integer.parseInt(id)-1));
            }
        }
        
    }
    
    public void placeCard(javafx.event.Event evt) throws Exception{
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        Card placing = GameController.getInstance().getCardPlacing();
        if(evt.getSource().toString().contains("Character")){
            //Berarti yang bisa dimasukkan adalah kartu KARAKTER
            if(placing instanceof com.avatarduel.model.card.Character){
                if(player.field.getCharacterField().getCard(Integer.parseInt(id)-1)==null){
                    player.field.getCharacterField().placeCard(Integer.parseInt(id)-1,placing);
                    reloadFieldPane();
                    System.out.println("KETARUH");
                    events.notify(Event.CARD_PLACED,player.getName());
                }
            }
        }
        else{
            if(placing instanceof com.avatarduel.model.card.Skill){
                if(player.field.getSkillField().getCard(Integer.parseInt(id)-1)==null){
                    player.field.getSkillField().placeCard(Integer.parseInt(id)-1,placing);
                    reloadFieldPane();
                    System.out.println("KETARUH");
                    events.notify(Event.CARD_PLACED,player.getName());
                }
            }
            System.out.println("MASUKNYA KE SINI");
        }
        setOnClick("changeAttackMode");
    }
    
    public void changeAttackMode(javafx.event.Event evt) throws Exception{
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character") && player.field.getCharacterField().getCard(Integer.parseInt(id)-1)!=null){
            SummonedCard dest = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
            int constFactor = 1;
            dest.rotate();
            if (dest.isAttackMode()) {
                constFactor = -1;
            }
            CharacterFields.get(Integer.parseInt(id)-1).getTransforms().add(new Rotate(constFactor*90, 35, 42.5));
        }      
    }
    
    public void selectCard(javafx.event.Event evt) throws Exception{
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character")){
            //Berarti yang bisa dimasukkan adalah kartu KARAKTER
            if(player.field.getCharacterField().getCard(Integer.parseInt(id)-1)!=null){
                events.notify(Event.PASS_SELECTED_CARD,player.field.getCharacterField().getCard(Integer.parseInt(id)-1));
                Pane p = (Pane) evt.getSource();
                events.notify(Event.PASS_SELECTED_PANEID,p.getId());
                events.notify(Event.SELECTEDCARD,player.getName());
                System.out.println("Keubah jadi kuning");
                CharacterFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: yellow;");
                reloadFieldPane();
                events.notify(Event.SELECTEDCARD,player.getName());
            }
        }
        else{
            System.out.println("GBS SELECT SKILL DI BATTLE PHASE");
        }
    }
    
    public void useCard(javafx.event.Event evt) throws Exception{
        //CEK KARTU APA KALAU KARTU SENDIRI ILANGIN BORDER SETONCLICK BALIK KE SELECTCARD
        GameController g = GameController.getInstance();
        int idDestination = Integer.parseInt(evt.getSource().toString().replaceAll("[^1-6]",""));
        int idUsed = Integer.parseInt(g.getSelectedPaneID().replaceAll("[^1-6]", ""));
        Pane p = (Pane) evt.getSource();
        if(g.getSelectedPaneID().equals(p.getId())){
            //Tidak melakukan apa apa , hanya menghilangkan border kuning
        }
        else{
            SummonedCharacter used = g.getCardSelected();
            //Cek kondisi menyerang atau ga.
            //Kalau kondisi menyerang , cek yang diserang player.field.getCharacterField.get(i)
            //YANG DISERANG DALAM POSISI APA
            //KALAU SERANG JUGA POSISINYA
            //Attack dari karakter lawan tidak boleh lebih dari atau sama dengan kartu Used
            //Kalau serang berhasil player.field.getCharacterField().removeCard(idDestination)
            //playerlawan.setHP(Attack kartu used - attack kartu Destination)
            //REMOVE KARTU USED DARI LIST OF ENABLED

            //Kalau DEFEND POSISINYA
            //Defense dari karakter lawan tidak boleh lebih dari atau sama dengan attack karakter pemain.
            //Kalau serang berhasil player.field.getCharacterField().removeCard(idDestination)
            //TIDAK ADA SET HP LAWAN(TIDAK ADA PENGURANGAN)
            //REMOVE KARTU USED dari LIST Of enabled
            
        }
        //Set Border ilang (unselect) setOnClick selectCard
        if(evt.getSource().toString().contains("Character")){
            CharacterFields.get(idUsed-1).setStyle("-fx-border-color: black;");
        }
        else{
            SkillFields.get(idDestination-1).setStyle("-fx-border-color: black;");
        }
        setOnClick("selectCard");
    }
}
