package com.avatarduel.view.controller;
import com.avatarduel.exceptions.EndGameException;
import com.avatarduel.exceptions.field.*;
import com.avatarduel.view.event.Event;
import com.avatarduel.view.event.EventManager;
import com.avatarduel.view.loader.GameLoader;
import com.avatarduel.view.loader.MessageBoxLoader;
import com.avatarduel.view.loader.MiniCardLoader;
import com.avatarduel.model.Player;
import com.avatarduel.model.SummonedCard;
import com.avatarduel.model.SummonedCharacter;
import com.avatarduel.model.card.Attribute;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.effect.Aura;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.avatarduel.model.field.CharacterField;
import com.avatarduel.model.field.SkillField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

/**
 * Controller for Player's Field
 */
public class FieldController implements Initializable{

    @FXML private List<Pane> CharacterFields;
    @FXML private List<Pane> SkillFields;
    private List<String> Enabled;
    private List<String> disabledInBattle;
    private String onClickArgs;
    private Player player;
    private EventManager events;
    private boolean delete;

    /**
     * Creates a new controller, for specified player.
     * @param player Destined player
     * @throws Exception exception when field controller cannot be instantiated
     */
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
    
    /**
     * Set which function to call by the onClick function.
     * @param args String command for onClick function. 
     */
    public void setOnClick(String args){
        this.onClickArgs = args;
    }
    
    /**
     * Set the availability of the deleteCard function to value.
     * @param value boolean Setter
     */
    public void setDelete(boolean value){
        this.delete = value;
    }
    
    /**
     * Set the avaibility of the onClick function to value.
     * @param value boolean Setter
     */
    public void setEnableClick(boolean value){
        if(value){
            enableAll();
        }
        else{
            disableAll();
        }
    }
    
    /**
     * Initialize all needed attributes for FieldController, when constructed.
     * @param location URL
     * @param resources ResourceBundle
     */
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
//            
        }
    }
    
    /**
     * Refresh the field pane display.
     * @throws Exception exception when field pane cannot be reloaded
     */
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
    
    /**
     * Reloads the border on the whole field to black. 
     */
    public void reloadBorder(){
        for(int i = 0; i < 6;i++){
           CharacterFields.get(i).setStyle("-fx-border-color:black;");
           SkillFields.get(i).setStyle("-fx-border-color:black;");
        }
    }
    
    /**
     * Set to enable onClick only on skill section of the field.
     */
    public void enableSkill(){
        for(int i=0; i < 6 ;i++){
            Enabled.add(SkillFields.get(i).getId());
        }
    }
    
    /**
     * Set to enable onClick function on the whole field.
     */
    public void enableAll(){
        disableAll();
        enableCharacter();
        enableSkill();
    }
    
    /**
     * Set to enable onClick function on the enableCharacter section.
     */
    public void enableCharacter(){
        for(int i=0; i < 6 ; i++){
            Enabled.add(CharacterFields.get(i).getId());
        }
    }
    
    /**
     * Set to enable onClick on specific pane using paneID.
     * @param paneId String specific PaneID
     */
    public void enableSpecific(String paneId){
        Enabled.add(paneId);
    }
    
    /**
     * Set to disable onClick on skill section of the field.
     */
    public void disableSkill(){
        for(int i=0; i < 6 ;i++){
            Enabled.remove(SkillFields.get(i).getId());
        }
    }
    
    /**
     * Set to disable onClick on character section of the field.
     */
    public void disableCharacter(){
        for(int i=0; i < 6 ; i++){
            Enabled.remove(CharacterFields.get(i).getId());
        }
    }
    
    /**
     * Set to disable specific pane using paneID.
     * @param paneId String pane id
     */
    public void disableSpecific(String paneId){
        Enabled.remove(paneId);
    }
    
    /**
     * Set to disable onClick function on the whole field.
     */
    public void disableAll(){
        Enabled = new ArrayList<>();
    }
    
    /**
     * Get list of disabled pane in battle phase.
     * @return String list of disabled paneid
     */
    public List<String> getDisabledInBattle() {
        return disabledInBattle;
    }

    /**
     * Clearing list of disabled pane in battle phase.
     */
    public void clearDisabledInBattle() {
        disabledInBattle.clear();
    }
    
    /**
     * Getting the value of the availability of deleteCard function.
     * @return boolean delete value of this
     */
    public boolean delete(){
        return this.delete;
    }
    
    /**
     * Call function depending on the onClickArgs.
     * @param evt event that triggers onClick
     * @throws Exception exception when onClick event fails
     */
    @FXML
    public void onClick(javafx.event.Event evt) throws Exception{
       try{
        Pane p = (Pane) evt.getSource();
        int id;
         id = Integer.parseInt(p.getId().replaceAll("[^1-6]",""));
         if(Enabled.contains(p.getId())){
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
             switch(onClickArgs){
               case "placeCard":
                 throw new ClickNotAllowedException("Cannot place into opponent field");
                case "selectCard":
                 throw new ClickNotAllowedException("Cannot select opponent card");
                case "useCard":
                 throw new ClickNotAllowedException("Cannot attack your own\ncard/skill card");
                case "modify":
                  throw new ClickNotAllowedException("Cannot modify opponent card");
                case "attachSkill":
                  throw new ClickNotAllowedException("Cannot attach skill to skill");
             }
         }
       }
       catch (Exception e){
           new MessageBoxLoader(e).render();
       }
    }
    
    /**
     * Set the cardViewer to display event sender image.
     * @param evt event that trigger onHover function
     * @throws Exception exception when onHover event fails
     */
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
    
    /**
     * Reduce enemy HP and set the HP text.
     * @param evt clicked enemy pane
     * @throws Exception EndGameException
     */
    public void attackHP(javafx.event.Event evt) throws Exception {
        Player opponent;
        String opponentId;
        GameController g = GameController.getInstance();
        int idUsed = Integer.parseInt(g.getSelectedPaneID().substring(0,g.getSelectedPaneID().indexOf(' ')).replaceAll("[^1-6]", ""));
        opponent = player;
        if (g.getP1().equals(player)) { opponentId = "P1"; } else { opponentId = "P2"; }
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
        try {
            if (g.isEndGame()) {
                throw new EndGameException(player.getName() + " Kehabisan HP");
            }
        } catch (Exception e) {
            new MessageBoxLoader(e).render();
            GameLoader.getInstance().exit();
        }

    }
    
    /**
     * Attach skill from placed card to evt sender pane.
     * @param evt clicked skill destination pane
     * @throws Exception exception when attach skill event fails
     */
    public void attachSkill(javafx.event.Event evt) throws Exception{
        try{
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
            else{
                throw new WrongPlacementException("No character card");
            }
        }
        catch(Exception e){
            new MessageBoxLoader(e).render();
        }
        
    }
    
    /**
     * Place card taken from hands to event sender pane.
     * @param evt clicked pane event
     * @throws Exception exception when place card event fails
     */
    public void placeCard(javafx.event.Event evt) throws Exception{
        try{
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
                    else{
                        throw new WrongPlacementException("Target location is not empty");
                    }
                }
                else{
                    throw new WrongPlacementException("Cannot place skill to\ncharacter field.");
                }
            }
             else {
                if(placing instanceof Skill){
                    if(player.field.getSkillField().getCard(Integer.parseInt(id)-1)==null){
                        player.field.getSkillField().placeCard(Integer.parseInt(id)-1,placing);
                        reloadFieldPane();
                        SkillFields.get(Integer.parseInt(id)-1).setStyle("-fx-border-color: red;");
                        GameController g = GameController.getInstance();
                        events.notify(Event.SKILL_LOCATION,"Skill"+Integer.parseInt(id)+" "+player.getName());
                        events.notify(Event.ATTACHING_SKILL,player.getName());
                    }
                    else{
                        throw new WrongPlacementException("Target location is not empty");
                    }
                }
                else{
                    throw new WrongPlacementException("Cannot place character\nto skill field");
                }
            }
        }
        catch(Exception e){
           new MessageBoxLoader(e).render();
        }
    }
    
    /**
     * Modify the clicked pane: change position and available to delete.
     * @param evt clicked pane event
     * @throws Exception exception when modify event fails
     */
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
    
    /**
     * Remove character including skill attached to it.
     * @param p Player owner of the character card
     * @param id index of the character card
     * @param location the SkillLocation list
     * @throws Exception exception when remove character fails
     */
    public void removeCharacter(Player p,int id,List<String> location) throws Exception {
        SummonedCard temp = p.field.getCharacterField().removeCard(id);
        if(!temp.isAttackMode()){
            CharacterFields.get(id).getTransforms().add(new Rotate(-1*90,35,42.5));
        }
        removeSkillsFromPane(location);
        GameController.getInstance().getP1FieldController().reloadFieldPane();
        GameController.getInstance().getP2FieldController().reloadFieldPane();
    }
    
    /**
     * Remove all skill card listed in location.
     * @param location list of string
     * @throws Exception exception when remove skill from pane fails
     */
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
    
    /**
     * Delete card specified by the red border.
     * @throws Exception exception when delete card fails
     */
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
    
    /**
     * Pass selected card prepared to battle.
     * @param evt event sender field pane
     * @throws Exception exception when selec card fails
     */
    public void selectCard(javafx.event.Event evt) throws Exception {
        try{
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
                        }
                        else{
                            throw new DefenseModeBattleException();
                        }
                    }
                } 
                else{
                    throw new BattleAllowedException("Card cannot battle in this round");
                }
            }
            else{
                throw new BattleAllowedException("Skill cannot participate in battle");
            }
        }
        catch(Exception e){
           new MessageBoxLoader(e).render();
        }
    }
    
    /**
     * Use the selected card (from selectCard) to attack the event sender field pane.
     * @param evt event sender field pane.
     * @throws Exception EndGameException if the enemy HP less or equal 0 when attacked
     */
    public void useCard(javafx.event.Event evt) throws Exception{
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
            try {
                if (used.getPositionValue() > destination.getPositionValue()) {
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
                        try {
                            if (g.isEndGame()) {
                                throw new EndGameException(player.getName() + " Kehabisan HP");
                            }
                        } catch (Exception e) {
                            new MessageBoxLoader(e).render();
                            GameLoader.getInstance().exit();
                        }
                    }
                    if (g.getP1().equals(player)) { g.getP2FieldController().getDisabledInBattle().add(g.getSelectedPaneID().split("\\s+")[0]); }
                    else { g.getP1FieldController().getDisabledInBattle().add(g.getSelectedPaneID().split("\\s+")[0]); }
                    events.notify(Event.RESET_SELECT_CARD, player.getName());
                } else {
                    throw new InvalidAttackException();
                }
            } catch (Exception e) {
                new MessageBoxLoader(e).render();
            }

        }
    }
}
