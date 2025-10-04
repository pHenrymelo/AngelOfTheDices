package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterCreateRequestDTO(
        @NotBlank String name,
        @NotBlank Origin origin,
        @NotBlank CharacterClass characterClass,
        @NotNull Path path,
        @NotNull Affinity affinity,
        @NotNull Integer nex,
        @NotNull Integer strength,
        @NotNull Integer agility,
        @NotNull Integer presence,
        @NotNull Integer intellect,
        @NotNull Integer vigor,
        @NotNull Integer maxHitPoints,
        @NotNull Integer maxEffortPoints,
        @NotNull Integer maxSanity,
        @NotBlank String rank,
        @NotNull Integer prestigePoints
) {}
