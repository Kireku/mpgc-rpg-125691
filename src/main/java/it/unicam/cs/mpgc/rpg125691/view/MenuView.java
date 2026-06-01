package it.unicam.cs.mpgc.rpg125691.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuView {
    private final VBox root;

    public MenuView(Runnable onNewGame, Runnable onLoadGame, Runnable onSaveGame, Runnable onCharacter, Runnable onQuests, Runnable onInventory, Runnable onBattle) {
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));
        this.root.setPrefWidth(180);

        Button newGameButton = createButton("New Game", onNewGame);
        Button loadGameButton = createButton("Load Game", onLoadGame);
        Button saveGameButton = createButton("Save Game", onSaveGame);
        Button characterButton = createButton("Character", onCharacter);
        Button questsButton = createButton("Quests", onQuests);
        Button inventoryButton = createButton("Inventory", onInventory);
        Button battleButton = createButton("Battle", onBattle);

        root.getChildren().addAll(newGameButton, loadGameButton, saveGameButton, characterButton, questsButton, inventoryButton, battleButton);
    }

    public Node getRoot() {
        return root;
    }

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(event -> action.run());
        return button;
    }
}
