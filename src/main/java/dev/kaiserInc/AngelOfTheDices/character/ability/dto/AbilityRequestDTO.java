package dev.kaiserInc.AngelOfTheDices.character.ability.dto;

import dev.kaiserInc.AngelOfTheDices.character.ability.AbilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbilityRequestDTO(
    @NotBlank(message = "Name cannot be blank")
    String name,

    @NotNull(message = "Type cannot be null")
    AbilityType type,

    String description
) {}
