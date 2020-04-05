package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.model.Player.Player;
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
    myself.draw();

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
      CardPack c = CardPack.getInstance();

      P1 = new Player(c.makeDeck());
      P2 = new Player(c.makeDeck());

    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}