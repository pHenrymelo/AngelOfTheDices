package dev.kaiserInc.AngelOfTheDices.character.classPath;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CharacterClass {

    COMBATANT("Combatente"),
    SPECIALIST("Especialista"),
    OCCULTIST("Ocultista"),
    SURVIVOR("Sobrevivente");

    private final String displayName;

    CharacterClass(String displayName) {
        this.displayName = displayName;
    }

    public String getName(){
        return this.name();
    }
}
