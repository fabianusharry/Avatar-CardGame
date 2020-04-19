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
     * @return instance of this object if its not null, else create a new one and return it
     * @throws IOException exception when create new loader
     *         for example: file not found error
     */
    public static MainMenuLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new MainMenuLoader();
        }
        return instance;
    }

    /**
     * Load fxml and set a controller
     *
     * @throws IOException exception when load fxml file and controller
     *         for example: file not found error
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
     * @return pane of this object
     */
    @Override
    public Pane getPane() {
        return mainMenu;
    }

    /**
     * create new stage and scene
     * show in new window
     */
    @Override
    public void render() {
        stage = new Stage();
        Scene scene = new Scene(mainMenu, 1360, 768);
        stage.setScene(scene);
        stage.setTitle(Constants.gameTitle);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * scene
     * show in new window
     *
     * @param stage is stage will be used
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
     * Close this stage
     */
    public void closeStage() {
        stage.close();
    }

    /**
     *
     * @return player 1 name
     * @throws Exception exception from controller
     */
    public String getP1Name() throws Exception {
        return controller.getName(1);
    }

    /**
     *
     * @return player 2 name
     * @throws Exception from controller
     */
    public String getP2Name() throws Exception {
        return controller.getName(2);
    }
}
