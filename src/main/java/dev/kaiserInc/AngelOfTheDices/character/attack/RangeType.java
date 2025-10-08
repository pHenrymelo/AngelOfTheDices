package dev.kaiserInc.AngelOfTheDices.character.attack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RangeType {
    PERSONAL("Pessoal"),
    TOUCH("Toque"),
    SHORT("Curto"),
    MEDIUM("Medio"),
    LONG("Longo"),
    EXTREME("Extremo");

    private final String displayName;

    RangeType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
