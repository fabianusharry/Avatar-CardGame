package com.avatarduel.view.loader;

import com.avatarduel.AvatarDuel;
import com.avatarduel.view.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import com.avatarduel.util.Constants;

public class MainMenuLoader implements Loader {
    private static MainMenuLoader instance = null; //Singleton attribute

    MainMenuController controller;
    Pane mainMenu;
    Stage stage;

    public static MainMenuLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new MainMenuLoader();
        }
        return instance;
    }

    private MainMenuLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.menuFmxl));
        controller = new MainMenuController();
        loader.setController(controller);
        // make stage
        this.mainMenu = loader.load();
    }

    public Pane getPane() {
        return mainMenu;
    }

    public void render() {
        stage = new Stage();
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.show();
    }

    public void render(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(AvatarDuel.class.getResource("momo.png").toString()));
        stage.show();
    }

    public void closeStage() {
        stage.close();
    }

    public String getP1Name() throws Exception {
        return controller.getName(1);
    }

    public String getP2Name() throws Exception {
        return controller.getName(2);
    }
}
