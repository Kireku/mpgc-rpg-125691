package it.unicam.cs.mpgc.rpg125691.model;

import java.util.Objects;

public class Stats {
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private int speed;

        public Stats(int maxHealth, int currentHealth, int attack, int defense, int speed) {
            if (maxHealth <= 0) {
                throw new IllegalArgumentException("maxHealth must be greater than 0");
            }
            if (attack < 0 || defense < 0 || speed < 0) {
                throw new IllegalArgumentException("stats must be non-negative");
            }
            if(currentHealth > maxHealth || currentHealth < 0) {
                throw new IllegalArgumentException("currentHealth must be between 0 and maxHealth");
            }
            this.maxHealth = maxHealth;
            this.currentHealth = currentHealth;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;

        }

        public int getMaxHealth() {
            return maxHealth;
        }
        public int getCurrentHealth() {
            return currentHealth;
        }
        public int getAttack(){
            return attack;
        }
        public int getDefense(){
            return defense;
        }
        public int getSpeed(){
            return speed;
        }
        public boolean isAlive(){
            return currentHealth > 0;
        }
        public void takeDamage(int amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Damage can't be negative");
            }
            currentHealth = Math.max(0, currentHealth - amount);
        }
        public void heal(int amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Heal can't be negative");
            }
            currentHealth = Math.min(maxHealth, currentHealth + amount);
        }
        public void restoreFullHealth() {
            currentHealth = maxHealth;
        }
    public void increaseMaxHealth(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Increase amount cannot be negative.");
        }

        maxHealth += amount;
        currentHealth += amount;
    }

    public void increaseAttack(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Increase amount cannot be negative.");
        }

        attack += amount;
    }

    public void increaseDefense(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Increase amount cannot be negative.");
        }

        defense += amount;
    }

    public void increaseSpeed(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Increase amount cannot be negative.");
        }

        speed += amount;
    }
    @Override
    public String toString() {
        return "HP" + currentHealth + "/" + maxHealth + " | ATK " + attack + " | DEF " + defense + " | SPEED " + speed;
    }

    @Override
    public boolean equals(Object o) {
            if(!(o instanceof Stats stats)){
                return false;
            }
            return maxHealth == stats.maxHealth &&
                    currentHealth == stats.currentHealth &&
                    attack == stats.attack &&
                    defense == stats.defense &&
                    speed == stats.speed;
    }
    @Override
    public int hashCode() {
            return Objects.hash(maxHealth, currentHealth, attack, defense, speed);
    }

}
