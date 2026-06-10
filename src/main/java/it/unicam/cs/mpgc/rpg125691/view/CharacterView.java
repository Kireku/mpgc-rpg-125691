package it.unicam.cs.mpgc.rpg125691.view;

import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CharacterView {
    private final VBox root;

    public CharacterView(PlayerCharacter player) {
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));

        Label title = new Label("Character");
        title.getStyleClass().add("section-title");

        root.getChildren().addAll(
                title,
                new Label("Name: " + player.getName()),
                new Label("Class: " + player.getCharacterClass()),
                new Label("Level: " + player.getLevel()),
                new Label("Experience: " + player.getExperience()),
                new Label("Gold: " + player.getGold()),
                new Label("Stats: " + player.getStats())
        );
    }

    public Node getRoot() {
        return root;
    }

}
