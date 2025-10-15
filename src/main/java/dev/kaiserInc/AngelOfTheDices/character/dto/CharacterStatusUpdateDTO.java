package dev.kaiserInc.AngelOfTheDices.character.dto;

import jakarta.validation.constraints.Min;

public record CharacterStatusUpdateDTO(
        @Min(value = 0, message = "Hit points cannot be negative.")
        Integer currentHitPoints,
        @Min(value = 0, message = "Effort points cannot be negative.")
        Integer currentEffortPoints,
        @Min(value = 0, message = "Sanity cannot be negative.")
        Integer currentSanity,
        @Min(value = 0, message = "Determination cannot be negative.")
        Integer currentDeterminationPoints
) {}
