package it.unicam.cs.mpgc.rpg125691.view;

import it.unicam.cs.mpgc.rpg125691.controller.MainController;
import it.unicam.cs.mpgc.rpg125691.model.CharacterClass;
import it.unicam.cs.mpgc.rpg125691.model.Inventory;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Optional;


public class MainView {

    private final MainController controller;
    private final BorderPane root;
    private final VBox contentBox;
    private final LogView logView;

    public MainView(MainController controller) {
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null.");
        }

        this.controller = controller;
        this.root = new BorderPane();
        this.contentBox = new VBox(10);
        this.logView = new LogView();

        configureLayout();
        showWelcomeView();
    }

    public Parent getRoot() {
        return root;
    }

    private void configureLayout() {
        root.setPadding(new Insets(15));
        contentBox.setPadding(new Insets(15));

        MenuView menuView = new MenuView(
                controller::handleNewGame,
                controller::handleLoadGame,
                controller::handleSaveGame,
                controller::handleShowCharacter,
                controller::handleShowQuests,
                controller::handleShowInventory,
                controller::handleShowBattle
        );

        root.setLeft(menuView.getRoot());
        root.setCenter(contentBox);
        root.setBottom(logView.getRoot());
    }

    public void showWelcomeView() {
        contentBox.getChildren().clear();

        contentBox.getChildren().addAll(
                new Label("RPG Quest Manager"),
                new Label("Create a new game or load an existing save.")
        );

        appendLog("Application started.");
    }

    public Optional<String> askPlayerName() {
        TextInputDialog nameDialog = new TextInputDialog("Hero");
        nameDialog.setTitle("New Game");
        nameDialog.setHeaderText("Create your character");
        nameDialog.setContentText("Character name:");

        return nameDialog.showAndWait();
    }

    public Optional<CharacterClass> askCharacterClass() {
        ComboBox<CharacterClass> classComboBox = new ComboBox<>();
        classComboBox.getItems().addAll(CharacterClass.values());
        classComboBox.setValue(CharacterClass.WARRIOR);

        Alert classDialog = new Alert(Alert.AlertType.CONFIRMATION);
        classDialog.setTitle("Character Class");
        classDialog.setHeaderText("Choose a character class");
        classDialog.getDialogPane().setContent(classComboBox);

        Optional<ButtonType> result = classDialog.showAndWait();

        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(classComboBox.getValue());
    }

    public void showCharacter(PlayerCharacter player) {
        setContent(new CharacterView(player).getRoot());
    }

    public void showInventory(Inventory inventory) {
        setContent(new InventoryView(inventory).getRoot());
    }

    public void showQuests(List<Quest> quests) {
        setContent(new QuestView(quests, controller::handleStartQuest).getRoot());
    }

    public void showBattle(PlayerCharacter player, Quest activeQuest) {
        setContent(new BattleView(player, activeQuest, controller::handleBattleAction).getRoot());
    }

    public void appendLog(String message) {
        logView.appendMessage(message);
    }

    private void setContent(Node node) {
        contentBox.getChildren().clear();
        contentBox.getChildren().add(node);
    }
}