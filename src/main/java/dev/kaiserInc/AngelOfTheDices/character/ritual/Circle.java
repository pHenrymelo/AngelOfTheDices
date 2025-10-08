package dev.kaiserInc.AngelOfTheDices.character.ritual;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Circle {
    FIRST("1°"),
    SECOND("2°"),
    THIRD("3°"),
    FOURTH("4°");

    private final String displayName;

    Circle(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
