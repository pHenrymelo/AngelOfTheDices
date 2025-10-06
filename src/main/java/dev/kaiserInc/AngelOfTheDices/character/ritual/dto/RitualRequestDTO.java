package dev.kaiserInc.AngelOfTheDices.character.ritual.dto;

import dev.kaiserInc.AngelOfTheDices.character.ritual.Circle;
import dev.kaiserInc.AngelOfTheDices.character.ritual.Element;
import dev.kaiserInc.AngelOfTheDices.character.ritual.ExecutionType;
import dev.kaiserInc.AngelOfTheDices.character.ritual.RangeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RitualRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Element cannot be null")
        Element element,

        @NotNull(message = "Circle cannot be null")
        Circle circle,

        @NotNull(message = "Execution type cannot be null")
        ExecutionType execution,

        @NotNull(message = "Range cannot be null")
        RangeType range,

        String target,
        String duration,
        String resistance,
        String description
) {}
