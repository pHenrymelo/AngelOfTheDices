package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;

import java.util.UUID;

public record CharacterResponseDTO(
        UUID id,
        String name,
        Origin origin,
        CharacterClass charClass,
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
