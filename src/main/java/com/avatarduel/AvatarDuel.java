package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.Card;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
  boolean endGame;
  Player P1;
  Player P2;

  public void drawPhase(Player myself) {
    myself.takeCardFromDeck();

  }

public void mainPhase1(Player myself) {
    boolean isLandReleased = false;
    while (true) {

    }
  }

  public void battlePhase(Player attacker, Player defender) {

  }

  public void mainPhase2(Player myself) {

  }

  public void turn(Player myself, Player opponent) {
    drawPhase(myself);
    mainPhase1(myself);
    battlePhase(myself, opponent);
    mainPhase2(myself);
  }


  @Override
  public void start(Stage stage) throws IOException, URISyntaxException {
    endGame = false;

    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Group root = new Group();

    root.getChildren().add(text);
    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();


    try {
      CardLoader c = CardLoader.getInstance();

      P1 = new Player(c.makeDeck());
      P2 = new Player(c.makeDeck());

      // CardField P1Field = new CardField();
      // CardField P2Field = new CardField();

      // P1.getCardsInHand().stream().filter(o -> o instanceof com.avatarduel.model.Character).forEach(o-> System.out.println(o.getName() + " ATTACK: "+ o.activate("attack")));

      // for (Card o : P1.getCardsInHand()) {
      //   if (o instanceof com.avatarduel.model.Character) {
      //     P1Field.getCharacterField().placeCard(1, o, true);
      //   }
      // }
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}