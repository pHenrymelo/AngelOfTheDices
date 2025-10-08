package dev.kaiserInc.AngelOfTheDices.character.expertise;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TrainingRank {
    UNTRAINED(0),
    TRAINED(5),
    VETERAN(10),
    EXPERT(15);

    private final int bonus;

    TrainingRank(int bonus) {
        this.bonus = bonus;
    }

    public String getName() {
        return this.name();
    }
}
