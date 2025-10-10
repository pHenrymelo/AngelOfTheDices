package dev.kaiserInc.AngelOfTheDices.character.rank;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Rank {
    RECRUIT("Recruta", Map.of(
            1, 2,
            2, 0,
            3, 0,
            4, 0
    ), "Baixo"),
    OPERATOR("Operador", Map.of(
            1, 3,
            2, 1,
            3, 0,
            4, 0
    ), "Medio"),
    SPECIAL_AGENT("Agente Especial", Map.of(
            1, 3,
            2, 2,
            3, 1,
            4, 0
    ), "Medio"),
    OFFICIAL_OPS("Oficial de Operações", Map.of(
            1, 3,
            2, 3,
            3, 2,
            4, 1
    ), "Alto"),
    ELITE_AGENT("Agente de Elite", Map.of(
            1, 3,
            2, 3,
            3, 3,
            4, 2
    ), "Ilimitado");

    private final String displayName;
    private final Map<Integer, Integer> itemLimits;
    private final String creditLimit;

    Rank(String displayName, Map<Integer, Integer> itemLimits, String creditLimit) {
        this.displayName = displayName;
        this.itemLimits = itemLimits;
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return this.name();
    }
}
