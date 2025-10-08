package dev.kaiserInc.AngelOfTheDices.character.ability;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AbilityType {
    CLASS_ABILITY("Habilidade de Classe"),
    ORIGIN_POWER("Poder de Origem"),
    PARANORMAL_POWER("Poder Paranormal");

    private final String displayName;

    AbilityType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
