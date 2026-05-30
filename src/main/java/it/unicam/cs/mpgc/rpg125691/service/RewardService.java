package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.Enemy;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;


public class RewardService {
    public void rewardPlayerForEnemy(PlayerCharacter player, Enemy enemy) {
        if(player == null){
            throw new IllegalArgumentException("player cannot be null");
        }
        if(enemy == null){
            throw new IllegalArgumentException("enemy cannot be null");
        }

        player.addExperience(enemy.getExperienceReward());
        player.addGold(enemy.getGoldReward());
    }
}
