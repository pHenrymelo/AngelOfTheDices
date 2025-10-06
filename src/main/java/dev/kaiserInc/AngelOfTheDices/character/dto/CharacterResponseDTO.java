package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.Rank;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.expertise.CharacterExpertise;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.item.Item;
import dev.kaiserInc.AngelOfTheDices.attack.Attack;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record CharacterResponseDTO(
        UUID id,
        String name,
        Integer nex,
        String portraitURL,

        Origin origin,
        CharacterClass charClass,
        Path path,
        Affinity affinity,
        Rank rank,

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

        Integer maxLoad,
        Integer currentLoad,

        Integer prestigePoints,
        Map<Integer, Integer> itemLimitsByCategory,

        Set<CharacterExpertise> expertises,
        List<Item> inventory,
        List<Attack> attacks


) {}
