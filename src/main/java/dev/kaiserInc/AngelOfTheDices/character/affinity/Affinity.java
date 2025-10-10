package dev.kaiserInc.AngelOfTheDices.character.affinity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Affinity {
    NONE("Nenhuma"),
    BLOOD("Sangue"),
    DEATH("Morte"),
    ENERGY("Energia"),
    KNOWLEDGE("Conhecimento"),
    FEAR("Medo");

    private final String displayName;

    Affinity(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
