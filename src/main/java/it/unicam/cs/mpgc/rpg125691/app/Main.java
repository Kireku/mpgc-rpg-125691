package it.unicam.cs.mpgc.rpg125691.app;

import it.unicam.cs.mpgc.rpg125691.controller.MainController;
import it.unicam.cs.mpgc.rpg125691.persistence.JsonGameRepository;
import it.unicam.cs.mpgc.rpg125691.service.BattleService;
import it.unicam.cs.mpgc.rpg125691.service.GameFactory;
import it.unicam.cs.mpgc.rpg125691.service.GameService;
import it.unicam.cs.mpgc.rpg125691.service.QuestService;
import it.unicam.cs.mpgc.rpg125691.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Path;

/**
 * Application entry point.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameService gameService = new GameService(
                new GameFactory(),
                new QuestService(),
                new BattleService(),
                new JsonGameRepository(Path.of("saves", "save.json"))
        );

        MainController mainController = new MainController(gameService);
        MainView mainView = new MainView(mainController);
        mainController.setMainView(mainView);

        Scene scene = new Scene(mainView.getRoot(), 1000, 650);

        stage.setTitle("RPG Quest Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}