package dev.kaiserInc.AngelOfTheDices.character.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterCreateRequestDTO(
        @NotBlank String name,
        @NotBlank String origin,
        @NotBlank String char_class,
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
