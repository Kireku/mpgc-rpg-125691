package it.unicam.cs.mpgc.rpg125691.persistence;

import it.unicam.cs.mpgc.rpg125691.model.GameState;

import java.io.IOException;
import java.util.Optional;

public interface GameRepository {

    void save(GameState gameState) throws IOException;
    Optional<GameState> load() throws IOException;
    boolean saveExists();
}
