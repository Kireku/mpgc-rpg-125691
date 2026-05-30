package it.unicam.cs.mpgc.rpg125691.service;

import it.unicam.cs.mpgc.rpg125691.model.BattleAction;
import it.unicam.cs.mpgc.rpg125691.model.BattleResult;
import it.unicam.cs.mpgc.rpg125691.model.BattleTurnResult;
import it.unicam.cs.mpgc.rpg125691.model.Enemy;
import it.unicam.cs.mpgc.rpg125691.model.PlayerCharacter;

import java.util.Random;

public class BattleService {
    private static final int DEFENSE_REDUCTION_FACTOR = 2;
    private static final int POTION_HEALING_AMOUNT = 25;
    private static final int ESCAPE_SUCCESS_PERCENTAGE = 50;

    private final DamageCalculator damageCalculator;
    private final InventoryService inventoryService;
    private final RewardService rewardService;
    private final Random random;

    public BattleService() {
        this(new DamageCalculator(), new InventoryService(), new RewardService(), new Random());
    }

    public BattleService(
            DamageCalculator damageCalculator,
            InventoryService inventoryService,
            RewardService rewardService,
            Random random
    ) {
        if (damageCalculator == null) {
            throw new IllegalArgumentException("Damage calculator cannot be null.");
        }
        if (inventoryService == null) {
            throw new IllegalArgumentException("Inventory service cannot be null.");
        }
        if (rewardService == null) {
            throw new IllegalArgumentException("Reward service cannot be null.");
        }
        if (random == null) {
            throw new IllegalArgumentException("Random generator cannot be null.");
        }

        this.damageCalculator = damageCalculator;
        this.inventoryService = inventoryService;
        this.rewardService = rewardService;
        this.random = random;
    }

    public BattleTurnResult executeTurn(PlayerCharacter player, Enemy enemy, BattleAction action) {
        validateBattleParticipants(player, enemy);

        if (action == null) {
            throw new IllegalArgumentException("Battle action cannot be null.");
        }

        if (!player.isAlive()) {
            return new BattleTurnResult("The player has already been defeated.", BattleResult.PLAYER_LOSE);
        }

        if (!enemy.isAlive()) {
            return new BattleTurnResult("The enemy has already been defeated.", BattleResult.PLAYER_WIN);
        }

        return switch (action) {
            case ATTACK -> executeAttackTurn(player, enemy);
            case DEFEND -> executeDefendTurn(player, enemy);
            case USE_POTION -> executePotionTurn(player, enemy);
            case ESCAPE -> executeEscapeTurn(player, enemy);
        };
    }

    private BattleTurnResult executeAttackTurn(PlayerCharacter player, Enemy enemy) {
        int playerDamage = damageCalculator.calculateDamage(
                player.getStats().getAttack(),
                enemy.getStats().getDefense()
        );

        enemy.receiveDamage(playerDamage);

        String message = player.getName() +
                " attacks " +
                enemy.getName() +
                " and deals " +
                playerDamage +
                " damage.";

        if (!enemy.isAlive()) {
            rewardService.rewardPlayerForEnemy(player, enemy);

            message += " " +
                    enemy.getName() +
                    " is defeated. " +
                    player.getName() +
                    " gains " +
                    enemy.getExperienceReward() +
                    " experience and " +
                    enemy.getGoldReward() +
                    " gold.";

            return new BattleTurnResult(message, BattleResult.PLAYER_WIN);
        }

        return executeEnemyCounterAttack(player, enemy, message);
    }

    private BattleTurnResult executeDefendTurn(PlayerCharacter player, Enemy enemy) {
        int enemyDamage = damageCalculator.calculateReducedDamage(
                enemy.getStats().getAttack(),
                player.getStats().getDefense(),
                DEFENSE_REDUCTION_FACTOR
        );

        player.receiveDamage(enemyDamage);

        String message = player.getName() +
                " takes a defensive stance. " +
                enemy.getName() +
                " attacks and deals only " +
                enemyDamage +
                " damage.";

        return createResultAfterEnemyAttack(player, message);
    }

    private BattleTurnResult executePotionTurn(PlayerCharacter player, Enemy enemy) {
        boolean potionUsed = inventoryService.usePotion(player, POTION_HEALING_AMOUNT);

        String message;

        if (potionUsed) {
            message = player.getName() +
                    " uses a potion and recovers " +
                    POTION_HEALING_AMOUNT +
                    " health.";
        } else {
            message = player.getName() +
                    " tries to use a potion, but there are no potions in the inventory.";
        }

        return executeEnemyCounterAttack(player, enemy, message);
    }

    private BattleTurnResult executeEscapeTurn(PlayerCharacter player, Enemy enemy) {
        int chance = random.nextInt(100);

        if (chance < ESCAPE_SUCCESS_PERCENTAGE) {
            return new BattleTurnResult(player.getName() + " escapes from battle.", BattleResult.ESCAPED);
        }

        String message = player.getName() + " tries to escape but fails.";

        return executeEnemyCounterAttack(player, enemy, message);
    }

    private BattleTurnResult executeEnemyCounterAttack(PlayerCharacter player, Enemy enemy, String previousMessage) {
        int enemyDamage = damageCalculator.calculateDamage(
                enemy.getStats().getAttack(),
                player.getStats().getDefense()
        );

        player.receiveDamage(enemyDamage);

        String message = previousMessage +
                " " +
                enemy.getName() +
                " counterattacks and deals " +
                enemyDamage +
                " damage.";

        return createResultAfterEnemyAttack(player, message);
    }

    private BattleTurnResult createResultAfterEnemyAttack(PlayerCharacter player, String message) {
        if (!player.isAlive()) {
            message += " " + player.getName() + " is defeated.";
            return new BattleTurnResult(message, BattleResult.PLAYER_LOSE);
        }

        return new BattleTurnResult(message, BattleResult.ONGOING);
    }

    private void validateBattleParticipants(PlayerCharacter player, Enemy enemy) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (enemy == null) {
            throw new IllegalArgumentException("Enemy cannot be null.");
        }
    }
}
