package dev.kaiserInc.AngelOfTheDices.character.dto;

import jakarta.validation.constraints.NotBlank;

public record CharacterCreateRequestDTO(
        @NotBlank String name,
        @NotBlank String origin,
        @NotBlank String char_class,
        @NotBlank Integer nex,
        @NotBlank Integer strength,
        @NotBlank Integer agility,
        @NotBlank Integer presence,
        @NotBlank Integer intellect,
        @NotBlank Integer vigor,
        @NotBlank Integer maxHitPoints,
        @NotBlank Integer maxEffortPoints,
        @NotBlank Integer maxSanity,
        @NotBlank String rank,
        @NotBlank Integer prestigePoints
) {}
