package it.unicam.cs.mpgc.rpg125691.model;

public class Enemy extends GameCharacter {
    private final int experienceReward;
    private final int goldReward;

    public Enemy (String name, Stats stats, int experienceReward, int goldReward) {
        super(name, stats);

        if(experienceReward < 0 || goldReward < 0) {
            throw new IllegalArgumentException("Rewards cannot be negative");
        }
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
    }

    public int getExperienceReward() {
        return experienceReward;
    }
    public int getGoldReward() {
        return goldReward;
    }
}
