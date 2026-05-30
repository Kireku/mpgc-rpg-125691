package it.unicam.cs.mpgc.rpg125691.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("RPG Quest Manager");

        BorderPane root = new BorderPane();
        root.setCenter(title);

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("RPG Quest Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}