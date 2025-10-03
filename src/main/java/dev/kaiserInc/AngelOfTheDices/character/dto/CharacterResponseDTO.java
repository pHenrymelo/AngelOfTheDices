package dev.kaiserInc.AngelOfTheDices.character.dto;

import java.util.UUID;

public record CharacterResponseDTO(
        UUID id,
        String name,
        String origin,
        String char_class,
        Integer nex,
        Integer strength,
        Integer agility,
        Integer intellect,
        Integer presence,
        Integer vigor,
        Integer maxHitPoints,
        Integer currentHitPoints,
        Integer maxEffortPoints,
        Integer currentEffortPoints,
        Integer maxSanity,
        Integer currentSanity,
        String rank,
        Integer prestigePoints
) {}
