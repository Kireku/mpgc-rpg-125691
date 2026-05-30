package it.unicam.cs.mpgc.rpg125691.persistence;

import it.unicam.cs.mpgc.rpg125691.model.CharacterClass;
import it.unicam.cs.mpgc.rpg125691.model.Enemy;
import it.unicam.cs.mpgc.rpg125691.model.GameState;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import it.unicam.cs.mpgc.rpg125691.model.Stats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonGameRepositoryTest {

    @TempDir
    Path tempDirectory;

    @Test
    void saveShouldCreateSaveFile() throws IOException {
        Path savePath = tempDirectory.resolve("save.json");
        GameRepository repository = new JsonGameRepository(savePath);

        GameState gameState = createSampleGameState();

        repository.save(gameState);

        assertTrue(repository.saveExists());
    }

    @Test
    void loadShouldReturnSavedGameState() throws IOException {
        Path savePath = tempDirectory.resolve("save.json");
        GameRepository repository = new JsonGameRepository(savePath);

        GameState originalGameState = createSampleGameState();

        repository.save(originalGameState);
        Optional<GameState> loadedGameState = repository.load();

        assertTrue(loadedGameState.isPresent());
        assertEquals("Hero", loadedGameState.get().getPlayer().getName());
        assertEquals(1, loadedGameState.get().getQuests().size());
    }

    @Test
    void loadShouldReturnEmptyOptionalWhenSaveDoesNotExist() throws IOException {
        Path savePath = tempDirectory.resolve("missing-save.json");
        GameRepository repository = new JsonGameRepository(savePath);

        Optional<GameState> loadedGameState = repository.load();

        assertTrue(loadedGameState.isEmpty());
    }

    private GameState createSampleGameState() {
        PlayerCharacter player = new PlayerCharacter(
                "Hero",
                CharacterClass.WARRIOR,
                new Stats(100, 15, 5, 5, 10)
        );

        Enemy enemy = new Enemy(
                "Slime",
                new Stats(30, 6, 2, 2, 6),
                20,
                10
        );

        Quest quest = new Quest(
                "First Hunt",
                "Defeat the slime near the village.",
                enemy,
                15,
                25
        );

        GameState gameState = new GameState(player);
        gameState.addQuest(quest);

        return gameState;
    }
}
