package dev.kaiserInc.AngelOfTheDices.character.dto;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityMapper;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackMapper;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.item.Item;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemMapper;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteMapper;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualMapper;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CharacterMapper {

    private CharacterMapper() {}

    public static Character toEntity(CharacterCreateRequestDTO dto) {
        Character character = new Character();
        character.setName(dto.name());
        character.setAge(dto.age());
        character.setGender(dto.gender());
        character.setOrigin(dto.origin());
        character.setCharacterClass(dto.characterClass());
        character.setPath(dto.path());
        character.setAffinity(dto.affinity());
        character.setNex(dto.nex());
        character.setStrength(dto.strength());
        character.setAgility(dto.agility());
        character.setIntellect(dto.intellect());
        character.setPresence(dto.presence());
        character.setVigor(dto.vigor());
        character.setMaxHitPoints(dto.maxHitPoints());
        character.setMaxEffortPoints(dto.maxEffortPoints());
        character.setMaxSanity(dto.maxSanity());
        character.setRank(dto.rank());
        character.setPrestigePoints(dto.prestigePoints());
        character.setCurrentHitPoints(dto.maxHitPoints());
        character.setCurrentEffortPoints(dto.maxEffortPoints());
        character.setCurrentSanity(dto.maxSanity());
        character.setArmorDefenseBonus(dto.armorDefenseBonus() != null ? dto.armorDefenseBonus() : 0);
        character.setOtherDefenseBonus(dto.otherDefenseBonus() != null ? dto.otherDefenseBonus() : 0);
        return character;
    }

    public static void updateEntityFromDTO(CharacterUpdateRequestDTO dto, Character character) {
        character.setName(dto.name());
        character.setAge(dto.age());
        character.setGender(dto.gender());
        character.setOrigin(dto.origin());
        character.setCharacterClass(dto.characterClass());
        character.setPath(dto.path());
        character.setAffinity(dto.affinity());
        character.setNex(dto.nex());
        character.setStrength(dto.strength());
        character.setAgility(dto.agility());
        character.setIntellect(dto.intellect());
        character.setPresence(dto.presence());
        character.setVigor(dto.vigor());
        character.setMaxHitPoints(dto.maxHitPoints());
        character.setMaxEffortPoints(dto.maxEffortPoints());
        character.setMaxSanity(dto.maxSanity());
        character.setRank(dto.rank());
        character.setPrestigePoints(dto.prestigePoints());
        character.setArmorDefenseBonus(dto.armorDefenseBonus());
        character.setOtherDefenseBonus(dto.otherDefenseBonus());
    }


    public static CharacterResponseDTO toResponseDTO(Character character) {

        String playerName = (character.getUser() != null)
                ? character.getUser().getName()
                : "???";

        int pePerRound = (character.getNex() != null && character.getNex() >= 5)
                ? Math.round((float) character.getNex() / 5)
                : 1;


        int currentLoad = character.getInventory().stream().mapToInt(Item::getSpaces).sum();
        int maxLoad = character.getMaxLoad();

        int movement = 9;
        if (currentLoad > maxLoad) {
            movement = Math.max(3, movement - 3);
        }

        int baseDefense = 10;
        int agilityBonus = character.getAgility() != null ? character.getAgility() : 0;
        int armorBonus = character.getArmorDefenseBonus() != null ? character.getArmorDefenseBonus() : 0;
        int otherBonus = character.getOtherDefenseBonus() != null ? character.getOtherDefenseBonus() : 0;
        int defense = baseDefense + agilityBonus + armorBonus + otherBonus;

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

        List<NoteResponseDTO> noteDtos = character.getNotes().stream()
                .map(NoteMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new CharacterResponseDTO(
                // Bloco 1: IDs e Nomes
                character.getId(),
                character.getName(),
                playerName,

                // Bloco 2: Detalhes Pessoais
                character.getAge(),
                character.getGender(),
                character.getPortraitUrl(),

                // Bloco 3: Dados de Jogo Principais
                character.getNex(),
                character.getPrestigePoints(),
                character.getOrigin(),
                character.getCharacterClass(),
                character.getPath(),
                character.getAffinity(),
                character.getRank(),

                // Bloco 4: Atributos
                character.getStrength(), character.getAgility(), character.getIntellect(),
                character.getPresence(), character.getVigor(),

                // Bloco 5: Status
                character.getMaxHitPoints(), character.getCurrentHitPoints(),
                character.getMaxEffortPoints(), character.getCurrentEffortPoints(),
                character.getMaxSanity(), character.getCurrentSanity(),

                // Bloco 6: Dados Calculados
                maxLoad,
                currentLoad,
                pePerRound,
                movement,
                defense,

                // Bloco 7: Outros Dados
                itemLimits,

                // Bloco 8: Coleções
                character.getExpertises(),
                inventoryDtos,
                attackDtos,
                abilityDtos,
                ritualDtos,
                noteDtos
        );
    }
}
