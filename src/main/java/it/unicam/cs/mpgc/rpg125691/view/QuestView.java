package it.unicam.cs.mpgc.rpg125691.view;

import it.unicam.cs.mpgc.rpg125691.model.Quest;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class QuestView {
    private final VBox root;

    public QuestView(List<Quest> quests, Consumer<Quest> onQuestSelected) {
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));

        root.getChildren().add(new Label("Available Quests"));

        if (quests.isEmpty()) {
            root.getChildren().add(new Label("No available quests."));
            return;
        }

        for (Quest quest : quests) {
            Button questButton = new Button(quest.getTitle() + " - " + quest.getDescription());
            questButton.setMaxWidth(Double.MAX_VALUE);
            questButton.setOnAction(event -> onQuestSelected.accept(quest));
            root.getChildren().add(questButton);
        }
    }

    public Node getRoot() {
        return root;
    }
}
