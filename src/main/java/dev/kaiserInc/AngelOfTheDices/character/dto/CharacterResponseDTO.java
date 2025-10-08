package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.Rank;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.expertise.CharacterExpertise;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.item.Item;
import dev.kaiserInc.AngelOfTheDices.character.attack.Attack;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record CharacterResponseDTO(
        // Bloco 1: ID e Detalhes Pessoais
        UUID id,
        String name,
        String playerName,
        Integer age,
        String gender,
        String portraitUrl,

        // Bloco 2: Regras Principais
        Integer nex,
        Integer prestigePoints,
        Origin origin,
        CharacterClass characterClass,
        Path path,
        Affinity affinity,
        Rank rank,

        // Bloco 3: Atributos
        Integer strength, Integer agility, Integer intellect, Integer presence, Integer vigor,

        // Bloco 4: Status
        Integer maxHitPoints, Integer currentHitPoints,
        Integer maxEffortPoints, Integer currentEffortPoints,
        Integer maxSanity, Integer currentSanity,

        // Bloco 5: Dados Calculados
        Integer pePerRound,
        Integer movement,
        Integer defense,
        Integer maxLoad,
        Integer currentLoad,

        // Bloco 6: Coleções
        Set<CharacterExpertise> expertises,
        List<ItemResponseDTO> inventory,
        List<AttackResponseDTO> attacks,
        List<AbilityResponseDTO> abilities,
        List<RitualResponseDTO> rituals,
        List<NoteResponseDTO> notes
) {}
