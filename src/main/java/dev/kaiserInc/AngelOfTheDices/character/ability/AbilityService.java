package dev.kaiserInc.AngelOfTheDices.character.ability;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityMapper;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityRequestDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AbilityService {

    private final AbilitiesRepository abilitiesRepository;
    private final CharacterService characterService;

    @Autowired
    public AbilityService(AbilitiesRepository abilitiesRepository, CharacterService characterService) {
        this.abilitiesRepository = abilitiesRepository;
        this.characterService = characterService;
    }

    public Ability createAbilityForCharacter(UUID characterId, UUID userId, AbilityRequestDTO abilityDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Ability newAbility = AbilityMapper.toEntity(abilityDto);
        newAbility.setCharacter(character);

        return abilitiesRepository.save(newAbility);
    }

    public Set<Ability> findAllAbilitiesByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getAbilities();
    }

    public Ability findAbilityById(UUID characterId, UUID abilityId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        return abilitiesRepository.findById(abilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Ability not found."));
    }

    public Ability updateAbilityForCharacter(UUID characterId, UUID abilityId, UUID userId, AbilityRequestDTO abilityDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Ability abilityToUpdate = abilitiesRepository.findById(abilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Ability not found."));

        if (!abilityToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Ability does not belong to the specified character.");
        }

        AbilityMapper.updateEntityFromDTO(abilityDto, abilityToUpdate);

        return abilitiesRepository.save(abilityToUpdate);
    }

    public void deleteAbilityForCharacter(UUID characterId, UUID abilityId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Ability abilityToDelete = abilitiesRepository.findById(abilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Ability not found."));

        if (!abilityToDelete.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Ability does not belong to the specified character.");
        }

        abilitiesRepository.delete(abilityToDelete);

    }


}
