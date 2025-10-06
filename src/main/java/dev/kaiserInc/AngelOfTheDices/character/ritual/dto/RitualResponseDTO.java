package dev.kaiserInc.AngelOfTheDices.character.ritual.dto;

import dev.kaiserInc.AngelOfTheDices.character.ritual.Circle;
import dev.kaiserInc.AngelOfTheDices.character.ritual.Element;
import dev.kaiserInc.AngelOfTheDices.character.ritual.ExecutionType;
import dev.kaiserInc.AngelOfTheDices.character.ritual.RangeType;

import java.util.UUID;

public record RitualResponseDTO(
        UUID id,
        String name,
        Element element,
        Circle circle,
        ExecutionType execution,
        RangeType range,
        String target,
        String duration,
        String resistance,
        String description
) {}
