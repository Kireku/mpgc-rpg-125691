package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;
import it.unicam.cs.mpgc.rpg125691.model.Quest;
import it.unicam.cs.mpgc.rpg125691.model.QuestStatus;

public class QuestService {
    public void startQuest(Quest quest) {
        if(quest == null) {
            throw new IllegalArgumentException("quest cannot be null");
        }

        quest.start();
    }

    public void completeQuest(PlayerCharacter player, Quest quest) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (quest == null) {
            throw new IllegalArgumentException("Quest cannot be null.");
        }
        if (quest.getStatus() == QuestStatus.COMPLETED) {
            throw new IllegalStateException("Quest is already completed.");
        }
        quest.complete();
        player.addGold(quest.getGoldReward());
        player.addExperience(quest.getExperienceReward());
    }
}
