package dev.kaiserInc.AngelOfTheDices.character.classPath;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CharacterClass {

    COMBATANT("Combatente", 20, 2, 12, 4, 2, 3),
    SPECIALIST("Especialista", 16, 3, 16, 3, 3, 4),
    OCCULTIST("Ocultista", 12, 4, 20, 2, 4, 5),
    SURVIVOR("Sobrevivente", 8, 2, 8, 0, 0, 0);

    private final String displayName;
    private final int baseHitPoints;
    private final int baseEffortPoints;
    private final int baseSanity;
    private final int hpPerLevel;
    private final int epPerLevel;
    private final int sanPerLevel;

    CharacterClass(String displayName, int baseHitPoints, int baseEffortPoints, int baseSanity,
                   int hpPerLevel, int epPerLevel, int sanPerLevel) {
        this.displayName = displayName;
        this.baseHitPoints = baseHitPoints;
        this.baseEffortPoints = baseEffortPoints;
        this.baseSanity = baseSanity;
        this.hpPerLevel = hpPerLevel;
        this.epPerLevel = epPerLevel;
        this.sanPerLevel = sanPerLevel;
    }

    public String getName(){
        return this.name();
    }
}
