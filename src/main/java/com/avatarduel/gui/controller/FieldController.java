package com.avatarduel.gui.controller;
import com.avatarduel.gui.event.Event;
import com.avatarduel.gui.event.EventManager;
import com.avatarduel.gui.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.effect.Aura;
import com.avatarduel.model.field.CardField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class FieldController implements Initializable{

    @FXML private List<Pane> CharacterFields;
    @FXML private List<Pane> SkillFields;
    private List<String> Enabled;
    private List<String> disabledInBattle;
    private String onClickArgs;
    private Player player;
    private EventManager events;
    private boolean delete;


    public FieldController(Player player) throws Exception{
        this.player = player;
        Enabled = new ArrayList<>();
        disabledInBattle = new ArrayList<>();
        events = new EventManager(Event.CARD_PLACED,Event.CHANGE_CARD_VIEW,Event.PASS_SELECTED_CARD,Event.PASS_SELECTED_PANEID,Event.SELECTEDCARD, Event.RESET_SELECT_CARD,
        Event.ATTACHING_SKILL,Event.MODIFYING,Event.MODIFY_LOCATION,Event.SKILL_LOCATION);
        events.subscribe(Event.CARD_PLACED,GameController.getInstance());
        events.subscribe(Event.CHANGE_CARD_VIEW,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_CARD,GameController.getInstance());
        events.subscribe(Event.PASS_SELECTED_PANEID,GameController.getInstance());
        events.subscribe(Event.SELECTEDCARD,GameController.getInstance());
        events.subscribe(Event.RESET_SELECT_CARD,GameController.getInstance());
        events.subscribe(Event.ATTACHING_SKILL,GameController.getInstance());
        events.subscribe(Event.MODIFYING,GameController.getInstance());
        events.subscribe(Event.MODIFY_LOCATION,GameController.getInstance());
        events.subscribe(Event.SKILL_LOCATION,GameController.getInstance());
        delete = false;
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
    
    public void setDelete(boolean b){
        this.delete = b;
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
                    CharacterFields.get(i).getChildren().add(new MiniCardLoader(player.field.getCharacterField().getCard(i)).getPane());
                }
                if(s.getCard(i)!=null){
                    SkillFields.get(i).getChildren().add(new MiniCardLoader(player.field.getSkillField().getCard(i)).getPane());
                }
            }
            reloadBorder();
        }
        catch(IOException e){
//            e.printStackTrace();
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
        System.out.println("Berhasil dikurang");
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
    
    public boolean delete(){
        return this.delete;
    }
    
    @FXML
    public void onClick(javafx.event.Event evt) throws Exception{
        System.out.println(Enabled);
       Pane p = (Pane) evt.getSource();
       int id;
        id = Integer.parseInt(p.getId().replaceAll("[^1-6]",""));
        if(Enabled.contains(p.getId())){
            System.out.println("NGECLICK YANG BISA DICLICK");
           switch (onClickArgs){
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
               case "attackHP":
                attackHP(evt);
                break;
               default:
                break;
           }
        } else{
            System.out.println("NGECLICK YANG GA BISA DI CLICK");
            System.out.println(Enabled);
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

    public void attackHP(javafx.event.Event evt) throws Exception {
        Player opponent;
        String opponentId;
        GameController g = GameController.getInstance();
        int idUsed = Integer.parseInt(g.getSelectedPaneID().substring(0,g.getSelectedPaneID().indexOf(' ')).replaceAll("[^1-6]", ""));
        if (g.getP1().equals(player)) {
            opponent = g.getP1();
            opponentId = "P1";
        } else {
            opponent = g.getP2();
            opponentId = "P2";
        }
        opponent.reduceHP(g.getCardSelected().getPositionValue());
        if (opponentId.equals("P1")) {
            g.P1HP.setText("HP : " + opponent.getHP());
            g.setP1HPBar(opponent.getHP());
        } else {
            g.P2HP.setText("HP : " + opponent.getHP());
            g.setP2HPBar(opponent.getHP());
        }
        //Set Border ilang (unselect) setOnClick selectCard
        CharacterFields.get(idUsed-1).setStyle("-fx-border-color: black;");
        events.notify(Event.RESET_SELECT_CARD, player.getName());
        if (g.isEndGame()) {
            System.out.println("NOTIF END GAMEE");
            // NOTIF END GAME
        }
    }

    public void attachSkill(javafx.event.Event evt) throws Exception{
        System.out.println("MASUK ATTACH SKILL");
        GameController g = GameController.getInstance();
        Player withSkill;
        String cardPlacedLoc;
        String skillLocation = g.getSkillLocation();
        if(skillLocation.split(" ")[1].equals(g.getP1().getName())){
            withSkill = g.getP1();
        }else{
            withSkill = g.getP2();
        }
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        Skill skill = g.getSkillPlacing();
        int indexSkill = Integer.parseInt(skillLocation.substring(0,skillLocation.indexOf(' ')).replaceAll("[^0-6]",""));
        cardPlacedLoc = player.getName();
        SummonedCard s = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
        if(s!=null){
            skill.setTargetLocation("Character"+id+" "+player.getName());
            if(skill instanceof com.avatarduel.model.card.effect.Destroy){
                withSkill.field.getSkillField().removeCard(indexSkill-1);
                removeCharacter(player, Integer.parseInt(id)-1, player.field.getCharacterField().getCard(Integer.parseInt(id)-1).getSkillLocation());
            }
            else{
                skill.activate(player, Integer.parseInt(id)-1,skillLocation);
            }
            reloadBorder();
            reloadFieldPane();
            events.notify(Event.CARD_PLACED,withSkill.getName());
            setOnClick("modify");
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
                    disabledInBattle.add("Character"+id);
                    events.notify(Event.CARD_PLACED,player.getName());
                    setOnClick("modify");
                }
            }
        } else {
            if(placing instanceof Skill){
                System.out.println("PLACING SKILL...");
                if(player.field.getSkillField().getCard(Integer.parseInt(id)-1)==null){
                    player.field.getSkillField().placeCard(Integer.parseInt(id)-1,placing);
                    reloadFieldPane();
                    SkillFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
                    GameController g = GameController.getInstance();
                    events.notify(Event.SKILL_LOCATION,"Skill"+Integer.parseInt(id)+" "+player.getName());
                    events.notify(Event.ATTACHING_SKILL,player.getName());
                }
            }
        }
    }
    
    public void modify(javafx.event.Event evt) throws Exception{
        reloadBorder();
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character") && player.field.getCharacterField().getCard(Integer.parseInt(id)-1)!=null){ 
            SummonedCard dest = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
            int rotateFactor = 1;
            dest.rotate();
            if (dest.isAttackMode()) { rotateFactor = -1; }
            CharacterFields.get(Integer.parseInt(id)-1).getTransforms().add(new Rotate(rotateFactor*90, 35, 42.5));
            CharacterFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
            events.notify(Event.MODIFYING,player.getName());
            events.notify(Event.MODIFY_LOCATION,"Character"+(Integer.parseInt(id)-1));
            delete = true;
        }        
        else if(evt.getSource().toString().contains("Skill") && player.field.getSkillField().getCard(Integer.parseInt(id)-1)!=null){
            SkillFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
            events.notify(Event.MODIFYING,player.getName());
            events.notify(Event.MODIFY_LOCATION,"Skill"+(Integer.parseInt(id)-1));
            delete = true;
        }
    }
    
    public void removeCharacter(Player p,int id,List<String> location) throws Exception {
        SummonedCard temp = p.field.getCharacterField().removeCard(id);
        if(!temp.isAttackMode()){
            CharacterFields.get(id).getTransforms().add(new Rotate(-1*90,35,42.5));
        }
        removeSkillsFromPane(location);
        reloadFieldPane();
    }
    
    public void removeSkillsFromPane(List<String> location) throws Exception {
        GameController g = GameController.getInstance();
        Player target;
        for (String locations : location) {
            String idextractor = locations.substring(0,locations.indexOf(' '));
            int index = Integer.parseInt(idextractor.replaceAll("[^1-6]",""));
            if(locations.split(" ")[1].equals(g.getP1().getName())){
                target = g.getP1();
            }
            else{
                target = g.getP2();
            }
            target.field.getSkillField().removeCard(index-1);
            reloadFieldPane();
        }
    }
    
    public void deleteCard() throws Exception {
        GameController g = GameController.getInstance();
        String id = g.getModifyLocation().replaceAll("[^0-6]","");
        if(g.getModifyLocation().split(" ")[0].contains("Character")){
            List<String> location = player.field.getCharacterField().getCard(Integer.parseInt(id)).getSkillLocation();
            this.removeCharacter(player, Integer.parseInt(id), location);
        }
        else if(g.getModifyLocation().split(" ")[0].contains("Skill")){
            Player target;
            String i = g.getModifyLocation();
            Skill s = (Skill) player.field.getSkillField().getCard(Integer.parseInt(id));
            String characterLocation = s.getTargetLocation();//Lokasi kartu karakter yang harus di deaktifasi
            SummonedCard c;
            if(characterLocation.split(" ")[1].equals(g.getP1().getName())){
                target = g.getP1();
                c = (SummonedCharacter) target.field.getCharacterField().getCard(Integer.parseInt(id));
            }
            else{
                target = g.getP2();
                c = (SummonedCharacter) target.field.getCharacterField().getCard(Integer.parseInt(id));
            }
            c.removeSkillAttached(s);
            c.removeSkillLocation(i+" "+g.getModifyType());
            if(s instanceof com.avatarduel.model.card.effect.Aura){
                //Deactivate Aura
                int newAttack = c.getCharacter().getAttribute(Attribute.ATTACK) - ((Aura) s).getAttack();
                int newDefense = c.getCharacter().getAttribute(Attribute.DEFENSE) - ((Aura) s).getDefense();
                c.getCharacter().setAttribute(Attribute.ATTACK, newAttack);
                c.getCharacter().setAttribute(Attribute.DEFENSE, newDefense);
                player.field.getSkillField().removeCard(Integer.parseInt(id));
                g.getP1FieldController().reloadFieldPane();
                g.getP2FieldController().reloadFieldPane();
            }
        }
        delete = false;
    }
    
    public void selectCard(javafx.event.Event evt) throws Exception {
        System.out.println("MASUK SELECT CARD");
        String id = evt.getSource().toString().replaceAll("[^1-6]","");
        if(evt.getSource().toString().contains("Character")){
            if (!disabledInBattle.contains("Character"+id)) {
                SummonedCard summonedCard = player.field.getCharacterField().getCard(Integer.parseInt(id)-1);
                if(summonedCard != null){
                    if (summonedCard.isAttackMode()) {
                        events.notify(Event.PASS_SELECTED_CARD,player.field.getCharacterField().getCard(Integer.parseInt(id)-1));
                        Pane p = (Pane) evt.getSource();
                        GameController g = GameController.getInstance();
                        events.notify(Event.PASS_SELECTED_PANEID,p.getId()+" "+player.getName());
                        CharacterFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: deeppink;");
                        reloadFieldPane();
                        events.notify(Event.SELECTEDCARD,player.getName());
                    }    // ELSE THROW EXCEPTION DEFENSE CARD
                }
            } // ELSE THROW EXCEPTION TIDAK BISA DIPAKAI DI BATTLE INI
        }
        else{
            System.out.println("GBS SELECT SKILL DI BATTLE PHASE");
        }
    }
    
    public void useCard(javafx.event.Event evt) throws Exception{
        //CEK KARTU APA KALAU KARTU SENDIRI ILANGIN BORDER SETONCLICK BALIK KE SELECTCARD
        System.out.println("MASUK USE CARD");
        Player opponent = null;
        GameController g = GameController.getInstance();
        int idDestination = Integer.parseInt(evt.getSource().toString().replaceAll("[^1-6]",""))-1;
        int idUsed = Integer.parseInt(g.getSelectedPaneID().substring(0,g.getSelectedPaneID().indexOf(' ')).replaceAll("[^1-6]", ""))-1;
        Pane p = (Pane) evt.getSource();
        Player haveSelectedCard;
        if(g.getSelectedPaneID().split(" ")[1].equals(g.getP1().getName())) {
            haveSelectedCard = g.getP1();
            opponent = g.getP2();
        }else{
            haveSelectedCard = g.getP2();
            opponent = g.getP1();
        }
        if (g.getSelectedPaneID().contains(p.getId()) && player.equals(haveSelectedCard)) {
            events.notify(Event.RESET_SELECT_CARD, opponent.getName());
        } else {
            SummonedCard used = g.getCardSelected();
            SummonedCard destination = player.field.getCharacterField().getCard(idDestination);
            if (used.getPositionValue() > destination.getPositionValue()) {
                System.out.println("BISA ATTACK KOKKKK");
                // LAWAN BESERTA KARTU SKILL NYA HILANG DARI FIELD
                removeCharacter(player,idDestination,destination.getSkillLocation());
                if (destination.isAttackMode() || used.havePowerUp()) {
                    int diff = used.getPositionValue() - destination.getPositionValue();
                    player.reduceHP(diff);
                    if (player.equals(g.getP1())) {
                        g.P1HP.setText("HP : " + player.getHP());
                        g.setP1HPBar(player.getHP());
                    } else {
                        g.P2HP.setText("HP : " + player.getHP());
                        g.setP2HPBar(player.getHP());
                    }
                    if (g.getP1().equals(player)) {
                        g.getP2FieldController().getDisabledInBattle().add(g.getSelectedPaneID().split("\\s+")[0]);
                    } else {
                        g.getP1FieldController().getDisabledInBattle().add(g.getSelectedPaneID().split("\\s+")[0]);
                    }
                    if (g.isEndGame()) {
                        // NOTIF END GAME
                    }
                    events.notify(Event.RESET_SELECT_CARD, player.getName());
                }
            }
        }
        //Set Border ilang (unselect) setOnClick selectCard
    }
}
