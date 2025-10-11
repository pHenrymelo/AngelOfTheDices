package dev.kaiserInc.AngelOfTheDices.character.rank;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Rank {
    RECRUIT("Recruta", 0, Map.of(
            1, 2,
            2, 0,
            3, 0,
            4, 0
    ), "Baixo"),
    OPERATOR("Operador", 20, Map.of(
            1, 3,
            2, 1,
            3, 0,
            4, 0
    ), "Medio"),
    SPECIAL_AGENT("Agente Especial",50, Map.of(
            1, 3,
            2, 2,
            3, 1,
            4, 0
    ), "Medio"),
    OFFICIAL_OPS("Oficial de Operações",100, Map.of(
            1, 3,
            2, 3,
            3, 2,
            4, 1
    ), "Alto"),
    ELITE_AGENT("Agente de Elite",100, Map.of(
            1, 3,
            2, 3,
            3, 3,
            4, 2
    ), "Ilimitado");

    private final String displayName;
    private final int minPrestige;
    private final Map<Integer, Integer> itemLimits;
    private final String creditLimit;

    Rank(String displayName, int minPrestige, Map<Integer, Integer> itemLimits, String creditLimit) {
        this.displayName = displayName;
        this.minPrestige = minPrestige;
        this.itemLimits = itemLimits;
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return this.name();
    }
}
