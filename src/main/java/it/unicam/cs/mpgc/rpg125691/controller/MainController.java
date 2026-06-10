package it.unicam.cs.mpgc.rpg125691.controller;

import it.unicam.cs.mpgc.rpg125691.model.BattleAction;
import it.unicam.cs.mpgc.rpg125691.model.BattleResult;
import it.unicam.cs.mpgc.rpg125691.model.BattleTurnResult;
import it.unicam.cs.mpgc.rpg125691.model.CharacterClass;
import it.unicam.cs.mpgc.rpg125691.model.GameState;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import it.unicam.cs.mpgc.rpg125691.service.GameService;
import it.unicam.cs.mpgc.rpg125691.view.MainView;

import java.io.IOException;
import java.util.Optional;

public class MainController {
    private final GameService gameService;
    private MainView mainView;

    public MainController(GameService gameService) {
        if (gameService == null) {
            throw new IllegalArgumentException("Game service cannot be null.");
        }

        this.gameService = gameService;
    }

    public void setMainView(MainView mainView) {
        if (mainView == null) {
            throw new IllegalArgumentException("Main view cannot be null.");
        }

        this.mainView = mainView;
    }

    public void handleNewGame() {
        Optional<String> selectedName = mainView.askPlayerName();

        if (selectedName.isEmpty() || selectedName.get().isBlank()) {
            mainView.appendLog("New game cancelled.");
            return;
        }

        Optional<CharacterClass> selectedClass = mainView.askCharacterClass();

        if (selectedClass.isEmpty()) {
            mainView.appendLog("New game cancelled.");
            return;
        }

        GameState gameState = gameService.startNewGame(selectedName.get(), selectedClass.get());

        mainView.appendLog("New game started with player " + gameState.getPlayer().getName() + ".");
        mainView.showCharacter(gameState.getPlayer());
    }

    public void handleLoadGame() {
        try {
            Optional<GameState> loadedGame = gameService.loadGame();

            if (loadedGame.isPresent()) {
                mainView.appendLog("Game loaded.");
                mainView.showCharacter(loadedGame.get().getPlayer());
            } else {
                mainView.appendLog("No save file found.");
            }
        } catch (IOException e) {
            mainView.appendLog("Unable to load game.");
        }
    }

    public void handleSaveGame() {
        try {
            gameService.saveGame();
            mainView.appendLog("Game saved.");
        } catch (IOException e) {
            mainView.appendLog("Unable to save game.");
        } catch (IllegalStateException e) {
            mainView.appendLog("No game started. Nothing to save.");
        }
    }

    public void handleShowCharacter() {
        try {
            GameState gameState = gameService.getCurrentGameState();
            mainView.showCharacter(gameState.getPlayer());
        } catch (IllegalStateException e) {
            mainView.appendLog("Start or load a game first.");
        }
    }

    public void handleShowInventory() {
        try {
            GameState gameState = gameService.getCurrentGameState();
            mainView.showInventory(gameState.getPlayer().getInventory());
        } catch (IllegalStateException e) {
            mainView.appendLog("Start or load a game first.");
        }
    }

    public void handleShowQuests() {
        try {
            mainView.showQuests(gameService.getAvailableQuests());
        } catch (IllegalStateException e) {
            mainView.appendLog("Start or load a game first.");
        }
    }

    public void handleStartQuest(Quest quest) {
        try {
            gameService.startQuest(quest);
            mainView.appendLog("Quest started: " + quest.getTitle());
            showCurrentBattle();
        } catch (IllegalStateException e) {
            mainView.appendLog(e.getMessage());
        }
    }

    public void handleShowBattle() {
        try {
            showCurrentBattle();
        } catch (IllegalStateException e) {
            mainView.appendLog("Start or load a game first.");
        }
    }

    public void handleBattleAction(BattleAction action) {
        try {
            BattleTurnResult result = gameService.executeBattleAction(action);
            mainView.appendLog(result.getMessage());

            if (!result.isBattleOver()) {
                showCurrentBattle();
                return;
            }

            if (result.getBattleResult() == BattleResult.PLAYER_WIN) {
                mainView.appendLog("Quest completed.");
                mainView.showQuests(gameService.getAvailableQuests());
                return;
            }

            if (result.getBattleResult() == BattleResult.PLAYER_LOSE) {
                mainView.appendLog("You have been defeated.");
                mainView.showCharacter(gameService.getCurrentGameState().getPlayer());
                return;
            }

            if (result.getBattleResult() == BattleResult.ESCAPED) {
                mainView.appendLog("You escaped from the battle.");
                mainView.showQuests(gameService.getAvailableQuests());
            }
        } catch (IllegalStateException e) {
            mainView.appendLog(e.getMessage());
        }
    }


    private void showCurrentBattle() {
        mainView.showBattle(gameService.getCurrentGameState().getPlayer(), gameService.getActiveQuest());
    }

}
