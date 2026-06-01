package it.unicam.cs.mpgc.rpg125691.view;

import it.unicam.cs.mpgc.rpg125691.model.BattleAction;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class BattleView {
    private final VBox root;

    public BattleView(Quest activeQuest, Consumer<BattleAction> onBattleAction) {
        this.root = new VBox(10);
        this.root.setPadding(new Insets(15));

        if(activeQuest == null){
            root.getChildren().add(new Label("No quest active"));
            return;
        }

        Label title = new Label("Battle");
        Label enemyName = new Label("Enemy: " + activeQuest.getEnemy().getName());
        Label enemyStats = new Label("Enemy stats: " + activeQuest.getEnemy().getStats());

        Button attackButton = createActionButton("Attack", BattleAction.ATTACK, onBattleAction);
        Button defendButton = createActionButton("Defend", BattleAction.DEFEND, onBattleAction);
        Button potionButton = createActionButton("Use Potion", BattleAction.USE_POTION, onBattleAction);
        Button escapeButton = createActionButton("Escape", BattleAction.ESCAPE, onBattleAction);

        HBox actions = new HBox(10, attackButton, defendButton, potionButton, escapeButton);

        root.getChildren().addAll(title, enemyName, enemyStats, actions);
    }

    public Node getRoot() {
        return root;
    }

    private Button createActionButton(
            String text,
            BattleAction action,
            Consumer<BattleAction> onBattleAction
    ) {
        Button button = new Button(text);
        button.setOnAction(event -> onBattleAction.accept(action));
        return button;
    }
}

