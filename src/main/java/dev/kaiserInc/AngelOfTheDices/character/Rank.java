package dev.kaiserInc.AngelOfTheDices.character;

import lombok.Getter;

import java.util.Map;

@Getter
public enum Rank {
    RECRUIT("Recruta", Map.of(
            1, 2,
            2, 0,
            3, 0,
            4, 0
    )),
    OPERATOR("Operador", Map.of(
            1, 3,
            2, 1,
            3, 0,
            4, 0
    )),
    SPECIAL_AGENT("Agente Especial", Map.of(
            1, 3,
            2, 2,
            3, 1,
            4, 0
    )),
    OFFICIAL_OPS("Oficial de Operações", Map.of(
            1, 3,
            2, 3,
            3, 2,
            4, 1
    )),
    ELITE_AGENT("Agente de Elite", Map.of(
            1, 3,
            2, 3,
            3, 3,
            4, 2
    ));

    private final String displayName;
    private final Map<Integer, Integer> itemLimits;

    Rank(String displayName, Map<Integer, Integer> itemLimits) {
        this.displayName = displayName;
        this.itemLimits = itemLimits;
    }
}
