package com.avatarduel.gui.controller;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.field.CardField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private List<String> disabledInBattle;
    private String onClickArgs;
    private Player player;
    private EventManager events;


    public FieldController(Player player) throws Exception{
        this.player = player;
        Enabled = new ArrayList<>();
        disabledInBattle = new ArrayList<>();
        events = new EventManager(Event.CARD_PLACED,Event.CHANGE_CARD_VIEW,Event.PASS_SELECTED_CARD,Event.PASS_SELECTED_PANEID,Event.SELECTEDCARD, Event.RESET_SELECT_CARD,
        Event.ATTACHING_SKILL,Event.MODIFYING_CHARACTER,Event.MODIFYING_SKILL,Event.MODIFYING_TYPE,Event.SKILL_LOCATION);
        events.subscribe(Event.CARD_PLACED,GameController.getInstance());
        events.subscribe(Event.CHANGE_CARD_VIEW,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_CARD,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_PANEID,GameController.getInstance());
        events.subscribe(Event.SELECTEDCARD,GameController.getInstance());
        events.subscribe(Event.RESET_SELECT_CARD,GameController.getInstance());
        events.subscribe(Event.ATTACHING_SKILL,GameController.getInstance());
        events.subscribe(Event.MODIFYING_CHARACTER,GameController.getInstance());
        events.subscribe(Event.MODIFYING_SKILL,GameController.getInstance());
        events.subscribe(Event.MODIFYING_TYPE,GameController.getInstance());
        events.subscribe(Event.SKILL_LOCATION,GameController.getInstance());
        onClickArgs = "";
    }
    
    public void setOnClick(String args){
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
        reloadBorder();       
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

    public List<String> getDisabledInBattle() {
        return disabledInBattle;
    }

    public void clearDisabledInBattle() {
        disabledInBattle.clear();
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
               case "modify":
                modify(evt);
                break;
               case "attachSkill":
                attachSkill(evt);
                break;
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
    
    public void attachSkill(javafx.event.Event evt) throws Exception{
        GameController g = GameController.getInstance();
        Player opponent;
        boolean SkillFromHere=true;
        if(g.getSkillLocation().contains("P1")){
            if(player.equals(g.getP1())){
                //Berarti dari kartu sendiri
                opponent = g.getP2();
            }else{
                //Berarti dari lawan
                opponent = g.getP1();
                SkillFromHere=false;
            }
        }else{
            //Letak Skill dari P2
            if(player.equals(g.getP2())){
                //Berati dari kartu sendiri
                opponent = g.getP1();
            }else{
                //Berarti dari lawan
                opponent = g.getP2();
                SkillFromHere=false;
            }
        }
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        Skill skill = GameController.getInstance().getSkillPlacing();
        String location = GameController.getInstance().getSkillLocation();
        if(SkillFromHere){
            SummonedCard s = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
            if(s!=null){
                skill.activate(player, Integer.parseInt(id)-1, location);
                reloadBorder();
                events.notify(Event.CARD_PLACED,player.getName());
                setOnClick("modify");
            }
        }
        else{
            SummonedCard s = opponent.field.getCharacterField().getCard(Integer.parseInt(id)-1);
            if(s!=null){
                skill.activate(player, Integer.parseInt(id)-1, location);
                reloadBorder();
                events.notify(Event.CARD_PLACED,player.getName());
                setOnClick("modify");
            }
        } 
    }
    
    public void placeCard(javafx.event.Event evt) throws Exception{
        reloadBorder();
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        Card placing = GameController.getInstance().getCardPlacing();
        if(evt.getSource().toString().contains("Character")){
            //Berarti yang bisa dimasukkan adalah kartu KARAKTER
            if(placing instanceof Character){
                if(player.field.getCharacterField().getCard(Integer.parseInt(id)-1)==null){
                    player.field.getCharacterField().placeCard(Integer.parseInt(id)-1,placing);
                    reloadFieldPane();
                    System.out.println("KETARUH");
                    events.notify(Event.CARD_PLACED,player.getName());
                    setOnClick("modify");
                }
            }
        } else {
            if(placing instanceof Skill){
                if(player.field.getSkillField().getCard(Integer.parseInt(id)-1)==null){
                    player.field.getSkillField().placeCard(Integer.parseInt(id)-1,placing);
                    reloadFieldPane();
                    SkillFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
                    GameController g = GameController.getInstance();
                    if(player.equals(g.getP1())){
                        events.notify(Event.SKILL_LOCATION,"Skill"+(Integer.parseInt(id)-1)+" P1");
                    }
                    else{
                        events.notify(Event.SKILL_LOCATION,"Skill"+(Integer.parseInt(id)-1)+" P2");
                    }
                    
                    events.notify(Event.ATTACHING_SKILL,player.getName());
                }
            }
        }
    }
    
    public void modify(javafx.event.Event evt) throws Exception{
        reloadBorder();
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character") && player.field.getCharacterField().getCard(Integer.parseInt(id)-1)!=null){ 
            player.field.getCharacterField().getCard(Integer.parseInt(id)-1).rotate();
            CharacterFields.get(Integer.parseInt(id)-1).getTransforms().add(new Rotate(90, 35, 42.5));
            CharacterFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
            events.notify(Event.MODIFYING_TYPE,"Character");
            events.notify(Event.MODIFYING_CHARACTER,player.field.getCharacterField().getCard(Integer.parseInt(id)-1));
        }        
        else if(evt.getSource().toString().contains("Skill") && player.field.getSkillField().getCard(Integer.parseInt(id)-1)!=null){
            SkillFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
            events.notify(Event.MODIFYING_TYPE,"Skill");
            events.notify(Event.MODIFYING_CHARACTER,player.field.getSkillField().getCard(Integer.parseInt(id)-1));
        }
    }
    
    public void selectCard(javafx.event.Event evt) throws Exception {
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character")){
            //Berarti yang bisa dimasukkan adalah kartu KARAKTER
            SummonedCard summonedCard = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
            if(summonedCard != null){
                if (summonedCard.isAttackMode()) {
                    events.notify(Event.PASS_SELECTED_CARD,player.field.getCharacterField().getCard(Integer.parseInt(id)-1));
                    Pane p = (Pane) evt.getSource();
                    GameController g = GameController.getInstance();
                    if(player.equals(g.getP1())){
                        events.notify(Event.PASS_SELECTED_PANEID,p.getId()+" P1");
                    }
                    else{
                        events.notify(Event.PASS_SELECTED_PANEID,p.getId()+" P2");
                    }
                    events.notify(Event.SELECTEDCARD,player.getName());
                    System.out.println("Keubah jadi kuning");
                    CharacterFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: deeppink;");
                    reloadFieldPane();
                    events.notify(Event.SELECTEDCARD,player.getName());
                }
                // ELSE THROW EXCEPTION DEFENSE CARD
            }
        }
        else{
            System.out.println("GBS SELECT SKILL DI BATTLE PHASE");
        }
    }
    
    public void useCard(javafx.event.Event evt) throws Exception{
        //CEK KARTU APA KALAU KARTU SENDIRI ILANGIN BORDER SETONCLICK BALIK KE SELECTCARD
        Player opponent;
        String opponentId = null;
        GameController g = GameController.getInstance();
        int idDestination = Integer.parseInt(evt.getSource().toString().replaceAll("[^1-6]",""));
        int idUsed = Integer.parseInt(g.getSelectedPaneID().substring(0,g.getSelectedPaneID().indexOf(' ')).replaceAll("[^1-6]", ""));
        Pane p = (Pane) evt.getSource();
        boolean cardFromHere =true;
        if(g.getSelectedPaneID().contains("P1")) {
            if(player.equals(g.getP1())){
                //Berarti dari kartu sendiri
                opponent = g.getP2();
                opponentId = "P2";
            }else{
                //Berarti dari lawan
                opponent = g.getP1();
                opponentId = "P1";
                cardFromHere =false;
            }
        } else {
            //Letak Kartu dari P2
            if(player.equals(g.getP2())){
                //Berati dari kartu sendiri
                opponent = g.getP1();
            }else{
                //Berarti dari lawan
                opponent = g.getP2();
                cardFromHere =false;
            }
        }
        if (g.getSelectedPaneID().equals(p.getId()) && cardFromHere) {
            //Tidak melakukan apa apa, hanya menghilangkan border kuning
        } else {
            if (player.equals(g.getP1())) {
                opponent = g.getP2();

            } else {
                opponent = g.getP1();

            }
            SummonedCard used = g.getCardSelected();
            SummonedCard destination = opponent.field.getCharacterField().getCard(idDestination-1);
            if (used.getPositionValue() > destination.getPositionValue()) {
                // LAWAN BESERTA KARTU SKILL NYA HILANG DARI FIELD
                if (destination.isAttackMode()) {
                    int diff = used.getPositionValue() - destination.getPositionValue();
                    opponent.reduceHP(diff);
                    if (opponentId.equals("P1")) {
                        g.P1HP.setText("HP : " + opponent.getHP());
                    } else {
                        g.P2HP.setText("HP : " + opponent.getHP());
                    }
                    if (opponent.getHP() <= 0) {
                        // g.endGame = true
                    }
                }
            }
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
        } else {
            SkillFields.get(idDestination-1).setStyle("-fx-border-color: black;");
        }
        events.notify(Event.RESET_SELECT_CARD, player.getName());
        setOnClick("selectCard");
    }
}
