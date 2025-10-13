package dev.kaiserInc.AngelOfTheDices.character.attack;

import dev.kaiserInc.AngelOfTheDices.character.CharactersRepository;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackMapper;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AttackService {

    private final AttacksRepository attacksRepository;
    private final CharacterService characterService;
    private final CharactersRepository charactersRepository;

    @Autowired
    public AttackService(AttacksRepository attacksRepository, CharacterService characterService, CharactersRepository charactersRepository) {
        this.attacksRepository = attacksRepository;
        this.characterService = characterService;
        this.charactersRepository = charactersRepository;
    }



    public Attack createAttackForCharacter(UUID characterId, UUID userId, AttackRequestDTO attackDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Attack newAttack = AttackMapper.toEntity(attackDto);
        newAttack.setCharacter(character);

        return attacksRepository.save(newAttack);
    }

    public Set<Attack> findAllAttacksByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getAttacks();
    }

    public Attack findAttackById(UUID characterId, UUID attackId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        return attacksRepository.findById(attackId)
                .orElseThrow(() -> new ResourceNotFoundException("Attack not found."));
    }

    public Attack updateAttackForCharacter(UUID characterId, UUID attackId, UUID userId, AttackRequestDTO attackDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);
        Attack attackToUpdate = attacksRepository.findById(attackId)
                        .orElseThrow(() -> new ForbiddenAccessException("Attack not found."));
        if (!attackToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Attack does not belong to the specified character.");
        }

        AttackMapper.updateEntityFromDTO(attackDto, attackToUpdate);

        return attacksRepository.save(attackToUpdate);
    }

    public void deleteAttackForCharacter(UUID characterId, UUID attackId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Attack attackToDelete = character.getAttacks().stream()
                .filter(attack -> attack.getId().equals(attackId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Attack with id " + attackId + " not found in this character's list."));

        character.getAttacks().remove(attackToDelete);

        charactersRepository.save(character);
    }
}
