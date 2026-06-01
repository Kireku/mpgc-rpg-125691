package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.CharacterClass;
import it.unicam.cs.mpgc.rpg125691.model.Enemy;
import it.unicam.cs.mpgc.rpg125691.model.GameState;
import it.unicam.cs.mpgc.rpg125691.model.Item;
import it.unicam.cs.mpgc.rpg125691.model.ItemType;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import it.unicam.cs.mpgc.rpg125691.model.Stats;

public class GameFactory {
    public GameState createNewGame(String playerName, CharacterClass characterClass) {
        if(playerName == null || playerName.isBlank()){
            throw new IllegalArgumentException("Player name cannot be empty");
        }

        if(characterClass == null){
            throw new IllegalArgumentException("Character class cannot be null");
        }

        PlayerCharacter player = new PlayerCharacter(playerName, characterClass, createStatsForClass(characterClass));
        addinitialItems(player);
        GameState gameState = new GameState(player);
        addInitialQuests(gameState);

        return gameState;
    }

    private Stats createStatsForClass(CharacterClass characterClass) {
        return switch (characterClass){
            case WARRIOR -> new Stats(120, 120, 18,8,4);
            case RANGER -> new Stats(95, 95, 16,5,10);
            case MAGE -> new Stats(75, 75, 22,3,8);
        };
    }

    private void addinitialItems(PlayerCharacter player) {
        player.getInventory().addItem(new Item("Potion", "restore a small amount of health", ItemType.CONSUMABLE, 10));
        player.getInventory().addItem(new Item("Potion", "restore a small amount of health", ItemType.CONSUMABLE, 10));
    }

    private void addInitialQuests(GameState gameState) {
        Enemy slime = new Enemy("Slime", new Stats(30, 30, 6, 2,2), 20, 10);
        Quest FirstQuest = new Quest("First Hunt", "Defeat the slime near the village", slime, 15, 25);
        Enemy wolf = new Enemy("Forest Wolf", new Stats(55, 55, 12, 4, 7), 40, 25
        );

        Quest secondQuest = new Quest("Danger in the Woods", "A wolf is threatening the forest path.", wolf, 35, 50
        );

        Enemy bandit = new Enemy("Bandit", new Stats(75, 75, 15, 6, 5), 70, 45
        );

        Quest thirdQuest = new Quest("Road Ambush", "Defeat the bandit blocking the trade road.", bandit, 60, 80
        );

        gameState.addQuest(FirstQuest);
        gameState.addQuest(secondQuest);
        gameState.addQuest(thirdQuest);
    }


}
