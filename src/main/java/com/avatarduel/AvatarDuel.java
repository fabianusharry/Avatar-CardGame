package com.avatarduel;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.fxml.FXMLLoader;

import com.avatarduel.gui.Loader.CardLoader;
import com.avatarduel.gui.Loader.MainMenuLoader;
import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.*;

public class AvatarDuel extends Application {
    boolean endGame;
    Player P1;
    Player P2;

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
        stage.show();

        Parent root2 = FXMLLoader.load(AvatarDuel.class.getResource("fxml/loading.fxml"));
    
        Scene scene2 = new Scene(root2, 1280, 720);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene2);
        stage.show();
        // MainMenuLoader mainMenu = new MainMenuLoader();
        // mainMenu.render();



        try {
            P1 = new Player("ab");
            P2 = new Player("cd");

            for (Card o : P1.getHandCards().getCards()) {
//                CardLoader test = new CardLoader(o);
//                Scene scene1 = new Scene(test.getPane());
//                stage.setScene(scene1);
//                test.render();
//                root.getChildren().add(test.getPane());
            }

        } catch (Exception e) {
            //text.setText("Failed to load cards: " + e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}