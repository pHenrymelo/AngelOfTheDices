package dev.kaiserInc.AngelOfTheDices.character.dto;


import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.item.Item;

import java.util.Map;

public class CharacterMapper {
    public static CharacterResponseDTO toResponseDTO(Character character) {

        int currentLoad = character.getInventory().stream()
                .mapToInt(Item::getSpaces)
                .sum();

        int maxLoad = character.getMaxLoad();

        Map<Integer, Integer> itemLimits = character.getRank() != null
                ? character.getRank().getItemLimits()
                : Map.of();

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
                character.getInventory(),
                character.getAttacks()
        );
    }

}
