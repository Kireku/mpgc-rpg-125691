package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.BattleAction;
import it.unicam.cs.mpgc.rpg125691.model.BattleResult;
import it.unicam.cs.mpgc.rpg125691.model.BattleTurnResult;
import it.unicam.cs.mpgc.rpg125691.model.CharacterClass;
import it.unicam.cs.mpgc.rpg125691.model.GameState;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import it.unicam.cs.mpgc.rpg125691.persistence.GameRepository;


import java.util.List;
import java.io.IOException;
import java.util.Optional;

public class GameService {
    private final GameFactory gameFactory;
    private final QuestService questService;
    private final BattleService battleService;
    private final GameRepository gameRepository;
    private GameState currentGameState;
    private Quest activeQuest;

    public GameService(GameFactory gameFactory, QuestService questService, BattleService battleService, GameRepository gameRepository) {
        if (gameFactory == null) {
            throw new IllegalArgumentException("Game factory cannot be null.");
        }
        if (questService == null) {
            throw new IllegalArgumentException("Quest service cannot be null.");
        }
        if (battleService == null) {
            throw new IllegalArgumentException("Battle service cannot be null.");
        }
        if (gameRepository == null) {
            throw new IllegalArgumentException("Game repository cannot be null.");
        }
        this.gameFactory = gameFactory;
        this.questService = questService;
        this.battleService = battleService;
        this.gameRepository = gameRepository;
    }

    public GameState startNewGame(String playerName, CharacterClass characterClass) {
        currentGameState = gameFactory.createNewGame(playerName, characterClass);
        activeQuest = null;
        return currentGameState;
    }

    public Optional<GameState> loadGame() throws IOException{
        Optional<GameState> loadGameState = gameRepository.load();
        loadGameState.ifPresent(gameState -> {
            currentGameState = gameState;
            activeQuest = null;
        });

        return loadGameState;
    }

    public void saveGame() throws IOException {
        ensureGameStarted();
        gameRepository.save(currentGameState);
    }

    public GameState getCurrentGameState() {
        ensureGameStarted();
        return currentGameState;
    }

    public List<Quest> getAvailableQuests() {
        ensureGameStarted();
        return currentGameState.getAvailableQuests();
    }

    public List<Quest> getCompletedQuests() {
        ensureGameStarted();
        return currentGameState.getCompletedQuests();
    }

    public Quest getActiveQuest() {
        return activeQuest;
    }

    public void startQuest(Quest quest) {
        ensureGameStarted();

        if (activeQuest != null) {
            throw new IllegalStateException("There is already an active quest.");
        }

        questService.startQuest(quest);
        activeQuest = quest;
    }

    public BattleTurnResult executeBattleAction(BattleAction action) {
        ensureGameStarted();

        if (activeQuest == null) {
            throw new IllegalStateException("There is no active quest.");
        }

        BattleTurnResult result = battleService.executeTurn(
                currentGameState.getPlayer(),
                activeQuest.getEnemy(),
                action
        );

        if (result.getBattleResult() == BattleResult.PLAYER_WIN) {
            questService.completeQuest(currentGameState.getPlayer(), activeQuest);
            activeQuest = null;
        }

        if (result.getBattleResult() == BattleResult.ESCAPED ||
                result.getBattleResult() == BattleResult.PLAYER_LOSE) {
            activeQuest = null;
        }

        return result;
    }

    public boolean hasSave() {
        return gameRepository.saveExists();
    }

    private void ensureGameStarted() {
        if (currentGameState == null) {
            throw new IllegalStateException("No game has been started or loaded.");
        }
    }
}
