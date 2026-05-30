package it.unicam.cs.mpgc.rpg125691.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg125691.model.GameState;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class JsonGameRepository implements GameRepository {

    private final Path saveFilePath;
    private final Gson gson;

    public JsonGameRepository(Path saveFilePath) {
        if (saveFilePath == null) {
            throw new IllegalArgumentException("Save file path cannot be null.");
        }

        this.saveFilePath = saveFilePath;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void save(GameState gameState) throws IOException {
        if (gameState == null) {
            throw new IllegalArgumentException("Game state cannot be null.");
        }

        Path parentDirectory = saveFilePath.getParent();

        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        try (Writer writer = Files.newBufferedWriter(saveFilePath)) {
            gson.toJson(gameState, writer);
        }
    }

    @Override
    public Optional<GameState> load() throws IOException {
        if (!saveExists()) {
            return Optional.empty();
        }

        try (Reader reader = Files.newBufferedReader(saveFilePath)) {
            GameState gameState = gson.fromJson(reader, GameState.class);
            return Optional.ofNullable(gameState);
        }
    }

    @Override
    public boolean saveExists() {
        return Files.exists(saveFilePath);
    }
}