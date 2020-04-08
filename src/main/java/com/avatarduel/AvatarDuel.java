package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import com.avatarduel.gui.controller.GameController;
import com.avatarduel.gui.controller.MainMenuController;
import com.avatarduel.gui.loader.CardLoader;
import com.avatarduel.gui.loader.GameLoader;
import com.avatarduel.gui.loader.MainMenuLoader;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AvatarDuel extends Application {
    public boolean endGame;
    public Player P1;
    public Player P2;

    public void drawPhase(Player myself) {
        myself.draw();

    }

    public void mainPhase1(Player myself) {
        boolean isLandReleased = false;
        boolean end = false;
//        while (!end) {

//      if (button end phase clicked) {
////        end = true;
////      }

//        }
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
        text.setX(640);
        text.setY(360);
        text.setTextAlignment(TextAlignment.CENTER);

        StackPane root = new StackPane();

        root.getChildren().add(text);
        Scene scene = new Scene(root, 1280, 720);
        
        stage.setTitle("Avatar Duel");
        stage.setScene(scene);
//        stage.show();

//        MainMenuLoader mainMenu = new MainMenuLoader();
//        mainMenu.render();

        try {
//            System.out.println(MainMenuController.getName(1));
//            P1 = new Player(MainMenuController.getName(1));
//            P2 = new Player(MainMenuController.getName(2));

            P1 = new Player("Abc");
            P2 = new Player("Def");

            GameLoader test = new GameLoader(this);
            test.render();

//            for (Card o : P1.getHandCards().getCards()) {
//               CardLoader test1 = new CardLoader(o);
////               Scene scene1 = new Scene(test.getPane());
////               stage.setScene(scene1);
//               test1.render();
////               root.getChildren().add(test.getPane());
//            }

        } catch (Exception e) {
            text.setText("Failed to load cards: " + e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}