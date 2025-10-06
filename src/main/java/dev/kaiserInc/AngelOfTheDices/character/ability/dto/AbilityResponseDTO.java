package dev.kaiserInc.AngelOfTheDices.character.ability.dto;

import dev.kaiserInc.AngelOfTheDices.character.ability.AbilityType;

import java.util.UUID;

public record AbilityResponseDTO(
        UUID id,
        String name,
        String description,
        AbilityType type
) {}
