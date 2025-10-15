package dev.kaiserInc.AngelOfTheDices.character.classPath;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CharacterClass {

    COMBATANT("Combatente", 20, 2, 12, 4, 2, 3, 6, 3),
    SPECIALIST("Especialista", 16, 3, 16, 3, 3, 4, 8,4),
    OCCULTIST("Ocultista", 12, 4, 20, 2, 4, 5,10,5),
    SURVIVOR("Sobrevivente", 8, 2, 8, 2, 1, 2, 4, 2);

    private final String displayName;
    private final int baseHitPoints;
    private final int baseEffortPoints;
    private final int baseSanity;
    private final int baseDeterminationPoints;
    private final int hpPerLevel;
    private final int epPerLevel;
    private final int sanPerLevel;
    private final int dpPerLevel;

    CharacterClass(String displayName, int baseHitPoints, int baseEffortPoints, int baseSanity,
                   int hpPerLevel, int epPerLevel, int sanPerLevel,  int baseDeterminationPoints, int dpPerLevel) {
        this.displayName = displayName;
        this.baseHitPoints = baseHitPoints;
        this.baseEffortPoints = baseEffortPoints;
        this.baseSanity = baseSanity;
        this.hpPerLevel = hpPerLevel;
        this.epPerLevel = epPerLevel;
        this.sanPerLevel = sanPerLevel;
        this.baseDeterminationPoints = baseDeterminationPoints;
        this.dpPerLevel = dpPerLevel;
    }

    public String getName(){
        return this.name();
    }
}
