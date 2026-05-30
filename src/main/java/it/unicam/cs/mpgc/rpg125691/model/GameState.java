package it.unicam.cs.mpgc.rpg125691.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameState {

    private PlayerCharacter player;
    private final List<Quest> quests;

    public GameState(PlayerCharacter player) {
        this.player = Objects.requireNonNull(player, "Player must not be null.");
        this.quests = new ArrayList<>();
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = Objects.requireNonNull(player, "Player must not be null.");
    }

    public void addQuest(Quest quest) {
        quests.add(Objects.requireNonNull(quest, "Quest cannot be null."));
    }

    public List<Quest> getQuests() {
        return Collections.unmodifiableList(quests);
    }

    public List<Quest> getAvailableQuests() {
        return quests.stream().filter(quest -> quest.getStatus() == QuestStatus.AVAILABLE).toList();
    }

    public List<Quest> getCompletedQuests() {
        return quests.stream().filter(quest -> quest.getStatus() == QuestStatus.COMPLETED).toList();
    }


}
