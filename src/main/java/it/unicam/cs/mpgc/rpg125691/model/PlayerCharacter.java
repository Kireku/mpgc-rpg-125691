package it.unicam.cs.mpgc.rpg125691.model;

import java.util.Objects;


public class PlayerCharacter extends GameCharacter {
    private final CharacterClass characterClass;
    private final Inventory inventory;
    private int level;
    private int experience;
    private int gold;

    public PlayerCharacter(String name, CharacterClass characterClass, Stats stats) {
        super(name, stats);
        this.characterClass = Objects.requireNonNull(characterClass, "Character class cannot be null.");
        this.inventory = new Inventory();
        this.level = 1;
        this.experience = 0;
        this.gold = 0;
    }
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void addExperience(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Experience amount must be greater than 0.");
        }
        experience += amount;
        while (experience >= experienceRequiredForNextLevel()) {
            experience -= experienceRequiredForNextLevel();
            levelup();
        }
    }

    public void addGold(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Gold amount must be greater than 0.");
        }
        gold += amount;
    }

    public boolean spendGold(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Gold amount must be greater than 0.");
        }
        if(gold < amount) {
            return false;
        }

        gold -= amount;
        return true;
    }

    private int experienceRequiredForNextLevel() {
        return level * 100;
    }
    private void levelup() {
        level++;
        getStats().increaseMaxHealth(10);
        getStats().increaseAttack(2);
        getStats().increaseDefense(1);
        getStats().increaseSpeed(1);
        getStats().restoreFullHealth();
    }
}
