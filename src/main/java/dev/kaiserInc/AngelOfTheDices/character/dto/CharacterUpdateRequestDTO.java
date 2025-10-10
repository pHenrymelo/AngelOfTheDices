package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.affinity.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.rank.Rank;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterUpdateRequestDTO(
        @NotBlank String name,
        Integer age,
        String gender,

        @NotNull Origin origin,
        @NotNull CharacterClass characterClass,
        @NotNull Path path,
        @NotNull Affinity affinity,
        @NotNull Rank rank,

        @NotNull @Min(0) Integer nex,
        @NotNull @Min(0) Integer prestigePoints,

        @NotNull Integer strength,
        @NotNull Integer agility,
        @NotNull Integer intellect,
        @NotNull Integer presence,
        @NotNull Integer vigor,

        @NotNull @Min(1) Integer maxHitPoints,
        @NotNull @Min(1) Integer maxEffortPoints,
        @NotNull @Min(1) Integer maxSanity,

        Integer armorDefenseBonus,
        Integer otherDefenseBonus
) {}
