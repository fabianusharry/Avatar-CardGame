package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import com.avatarduel.model.Character;
import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private List<Character> listCharacter = new ArrayList<Character>();
  private List<Land> listLand = new ArrayList<Land>();
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
    landReader.setSkipHeader(true);
    characterReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    for (String[] row : landRows) {
      listLand.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
    }
    List<String[]> characterRows = characterReader.read();
    for (String[] row : characterRows) {
      listCharacter.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), row[4]));
    }
  }

  @Override
  public void start(Stage stage) {
//    Text text = new Text();
//    text.setText("Loading...");
//    text.setX(50);
//    text.setY(50);

    Group root = new Group();





    try {
      this.loadCards();
//      text.setText("Avatar Duel!");
//      ImageView imageView  = new ImageView(new Image(getClass().getResource("card/image/character/Katara.png").toString()));
      ImageView imageView  = new ImageView(new Image(getClass().getResource("card/image/Character/Southern Water Temple.png").toString()));
//      //Setting the position of the image
      imageView.setX(50);
      imageView.setY(50);

      root.getChildren().add(imageView);
      Scene scene = new Scene(root, 1280, 720);

      stage.setTitle("Avatar Duel");
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
//      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}