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
    public void start(Stage stage) throws Exception {
        endGame = false;

        MainMenuLoader mainMenu = MainMenuLoader.getInstance();
        mainMenu.render(stage);

//        try {
//            P1 = new Player(mainMenu.getP1Name());
//            P2 = new Player(mainMenu.getP2Name());
//            for (int i = 0; i < 2; i++) {
//                P1.draw();
//            }


//            GameLoader game = new GameLoader(this);
//            stage.setScene(new Scene(game.getPane(), 1360, 768));

//            for (Card o : P1.getHandCards().getCards()) {
//               CardLoader test1 = new CardLoader(o);
////               Scene scene1 = new Scene(test.getPane());
////               stage.setScene(scene1);
//               test1.render();
////               root.getChildren().add(test.getPane());
//            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}