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

/**
 * MainMenuLoader in GUI
 * Contains pane for showing Main Menu
 */
public class MainMenuLoader implements Loader {
    private static MainMenuLoader instance = null; //Singleton attribute

    MainMenuController controller;
    Pane mainMenu;
    Stage stage;

    /**
     * Singleton design pattern implementation
     * @return instance
     * @throws IOException exception when fail to create MainMenuLoader object
     */
    public static MainMenuLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new MainMenuLoader();
        }
        return instance;
    }

    /**
     * Creates a new MainMenuLoader
     * @throws IOException exception when FXMLLoader fails to load resource
     */
    private MainMenuLoader() throws IOException {
        // init loader
        FXMLLoader loader = new FXMLLoader(AvatarDuel.class.getResource(Constants.menuFxml));
        controller = new MainMenuController();
        loader.setController(controller);
        // make stage
        this.mainMenu = loader.load();
    }

    /**
     * Get pane
     * @return pane
     */
    public Pane getPane() {
        return mainMenu;
    }

    /**
     * Render pane to new stage
     */
    public void render() {
        stage = new Stage();
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Render pane to specific stage
     * @param stage stage
     */
    public void render(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.getIcons().add(new Image(AvatarDuel.class.getResource("momo.png").toString()));
        stage.show();
    }

    /**
     * Close stage
     */
    public void closeStage() {
        stage.close();
    }

    /**
     * Get first player name
     * @return first player name
     * @throws Exception exception when fails to get name from controller
     */
    public String getP1Name() throws Exception {
        return controller.getName(1);
    }

    /**
     * Get second player name
     * @return second player name
     * @throws Exception exception when fails to get name from controller
     */
    public String getP2Name() throws Exception {
        return controller.getName(2);
    }
}
