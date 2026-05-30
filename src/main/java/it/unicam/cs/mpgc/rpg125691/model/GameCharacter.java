package it.unicam.cs.mpgc.rpg125691.model;

import java.util.Objects;

public abstract class GameCharacter {
    private final String name;
    private final Stats stats;
    protected GameCharacter(String name, Stats stats) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException("name cannot be empty");
        }

        this.name = name;
        this.stats = Objects.requireNonNull(stats, "stats cannot be null");
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean isAlive() {
        return stats.isAlive();
    }

    public void reciveDamage(int amount) {
        stats.takeDamage(amount);
    }

    public void heal(int amount) {
        stats.heal(amount);
    }
}
