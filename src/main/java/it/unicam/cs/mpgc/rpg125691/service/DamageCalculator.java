package it.unicam.cs.mpgc.rpg125691.service;

public class DamageCalculator {
    private static final int MINIMUM_DAMAGE = 1;

    public int calculateDamage(int attack, int defense) {
        if(attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Attack and defense cannot be negative.");
        }

        return Math.max(MINIMUM_DAMAGE, attack - defense);
    }

    public int calculateReducedDamage(int attack, int defense, int reductionFactor) {
        if(reductionFactor <= 0){
            throw new IllegalArgumentException("Reduction factor must be greater than zero.");
        }

        int baseDamage = calculateDamage(attack, defense);
        return Math.max(MINIMUM_DAMAGE, baseDamage / reductionFactor);
    }
}
