package dev.kaiserInc.AngelOfTheDices.character.dto;


import dev.kaiserInc.AngelOfTheDices.character.Character;

public class CharacterMapper {
    public static CharacterResponseDTO toResponseDTO(Character character) {
        return new CharacterResponseDTO(
            character.getId(), character.getName(), character.getOrigin(), character.getChar_class(),
            character.getNex(), character.getStrength(), character.getAgility(), character.getIntellect(),
            character.getPresence(), character.getVigor(), character.getMaxHitPoints(),
            character.getCurrentHitPoints(), character.getMaxEffortPoints(),
            character.getCurrentEffortPoints(), character.getMaxSanity(), character.getCurrentSanity(),
            character.getRank(), character.getPrestigePoints()
        );
    }

}
