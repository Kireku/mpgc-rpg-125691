package it.unicam.cs.mpgc.rpg125691.model;

public class BattleTurnResult {
    private final String message;
    private final BattleResult battleResult;

    public BattleTurnResult(String message, BattleResult battleResult) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if (battleResult == null) {
            throw new IllegalArgumentException("Battle cannot be null");
        }
        this.message = message;
        this.battleResult = battleResult;
    }

    public String getMessage() {
        return message;
    }

    public BattleResult getBattleResult() {
        return battleResult;
    }

    public boolean isBattleOver() {
        return battleResult != BattleResult.ONGOING;
    }
}
