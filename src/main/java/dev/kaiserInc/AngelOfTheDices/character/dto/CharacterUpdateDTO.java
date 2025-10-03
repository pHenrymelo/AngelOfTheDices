package dev.kaiserInc.AngelOfTheDices.character.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterUpdateDTO(
        @NotBlank String name,
        @NotBlank String origin,
        @NotBlank String char_class,
        @NotNull Integer nex,
        @NotNull Integer strength, @NotNull Integer agility, @NotNull Integer intellect,
        @NotNull Integer presence, @NotNull Integer vigor,
        @NotNull @Min(1) Integer maxHitPoints,
        @NotNull @Min(1) Integer maxEffortPoints,
        @NotNull @Min(1) Integer maxSanity,
        @NotBlank String rank,
        @NotNull Integer prestigePoints
) {}
