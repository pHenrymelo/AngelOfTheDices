package dev.kaiserInc.AngelOfTheDices.character.ritual;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Element {
    BLOOD("Sangue"),
    DEATH("Morte"),
    ENERGY("Energia"),
    KNOWLEDGE("Conhecimento"),
    FEAR("Medo");

    private final String displayName;

    Element(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
