package dev.kaiserInc.AngelOfTheDices.character.expertise;

public enum TrainingRank {
    UNTRAINED(0),
    TRAINED(5),
    VETERAN(10),
    EXPERT(15);

    private final int bonus;

    TrainingRank(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
