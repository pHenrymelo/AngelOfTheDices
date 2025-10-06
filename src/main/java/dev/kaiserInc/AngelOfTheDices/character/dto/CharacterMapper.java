package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityMapper;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackMapper;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.item.Item;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemMapper;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualMapper;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CharacterMapper {
    public static CharacterResponseDTO toResponseDTO(Character character) {

        int currentLoad = character.getInventory().stream()
                .mapToInt(Item::getSpaces)
                .sum();

        int maxLoad = character.getMaxLoad();

        Map<Integer, Integer> itemLimits = character.getRank() != null
                ? character.getRank().getItemLimits()
                : Map.of();

        List<ItemResponseDTO> inventoryDtos = character.getInventory().stream()
                .map(ItemMapper::toResponseDTO)
                .collect(Collectors.toList());

        List<AttackResponseDTO> attackDtos = character.getAttacks().stream()
                .map(AttackMapper::toResponseDTO)
                .collect(Collectors.toList());

        List<AbilityResponseDTO> abilityDtos = character.getAbilities().stream()
                .map(AbilityMapper::toResponseDTO)
                .collect(Collectors.toList());

        List<RitualResponseDTO> ritualDtos = character.getRituals().stream()
                .map(RitualMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new CharacterResponseDTO(
                character.getId(),
                character.getName(),
                character.getNex(),
                character.getPortraitUrl(),
                character.getOrigin(),
                character.getCharacterClass(),
                character.getPath(),
                character.getAffinity(),
                character.getRank(),
                character.getStrength(), character.getAgility(), character.getIntellect(),
                character.getPresence(), character.getVigor(),
                character.getMaxHitPoints(), character.getCurrentHitPoints(),
                character.getMaxEffortPoints(), character.getCurrentEffortPoints(),
                character.getMaxSanity(), character.getCurrentSanity(),
                maxLoad,
                currentLoad,
                character.getPrestigePoints(),
                itemLimits,
                character.getExpertises(),
                inventoryDtos,
                attackDtos,
                abilityDtos,
                ritualDtos

        );
    }
}
