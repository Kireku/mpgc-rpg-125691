package it.unicam.cs.mpgc.rpg125691.model;

import java.util.Objects;

public class Quest {
    private final String title;
    private final String description;
    private final Enemy enemy;
    private final int goldReward;
    private final int experienceReward;
    private QuestStatus status;

    public Quest(String title, String description, Enemy enemy, int goldReward, int experienceReward) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Quest title cannot be empty.");
        }
        if (goldReward < 0 || experienceReward < 0) {
            throw new IllegalArgumentException("Quest rewards cannot be negative.");
        }
        this.title = title;
        this.description = description == null ? "" : description;
        this.enemy = Objects.requireNonNull(enemy, "Enemy cannot be null.");
        this.goldReward = goldReward;
        this.experienceReward = experienceReward;
        this.status = QuestStatus.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void start() {
        if (status != QuestStatus.AVAILABLE) {
            throw new IllegalStateException("Quest is not AVAILABLE.");
        }
        status = QuestStatus.IN_PROGRESS;
    }

    public void complete() {
        if(status == QuestStatus.COMPLETED) {
            throw new IllegalStateException("Quest is COMPLETED.");
        }
        status = QuestStatus.COMPLETED;
    }

    public void cancel() {
        if(status != QuestStatus.IN_PROGRESS) {
            throw new IllegalStateException("Only in-progress quests can be cancelled.");
        }

        status = QuestStatus.AVAILABLE;
        enemy.getStats().restoreFullHealth();
    }
}

