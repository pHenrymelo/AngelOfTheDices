package dev.kaiserInc.AngelOfTheDices.character.ritual;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExecutionType {
    FREE("Ação Livre"),
    REACTION("Reação"),
    STANDARD("Ação Padrão"),
    COMPLETE("Ação Completa");

    private final String displayName;

    ExecutionType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }
}
