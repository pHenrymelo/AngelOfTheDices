package dev.kaiserInc.AngelOfTheDices.character.attack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AttackType {

    BALLISTIC("Balístico"),
    PIERCE("Perfuração"),
    IMPACT("Impacto"),
    CUT("Corte"),
    ELECTRICITY("Eletricidade"),
    FIRE("Fogo"),
    MENTAL("Mental"),
    PARANORMAL("Paranormal"),
    CHEMICAL("Químico");

    private final String displayName;

    AttackType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
