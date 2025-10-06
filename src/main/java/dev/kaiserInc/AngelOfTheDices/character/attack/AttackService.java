package dev.kaiserInc.AngelOfTheDices.character.attack;


import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbidenAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttackService {

    private final AttacksRepository attacksRepository;
    private final CharacterService characterService;

    @Autowired
    public AttackService(AttacksRepository attacksRepository, CharacterService characterService) {
        this.attacksRepository = attacksRepository;
        this.characterService = characterService;
    }

    public Attack createAttackForCharacter(UUID characterId, UUID userId, AttackRequestDTO attackDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        Attack newAttack = new Attack();
        mapDtoToEntity(attackDto, newAttack);
        newAttack.setCharacter(character);
        return attacksRepository.save(newAttack);
    }

    public List<Attack> findAllAttacksByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getAttacks();
    }

    public Attack updateAttackForCharacter(UUID characterId, UUID attackId, UUID userId, AttackRequestDTO attackDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);
        Attack attackToUpdate = attacksRepository.findById(attackId)
                        .orElseThrow(() -> new ForbidenAccessException("Attack not found."));
        if (!attackToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbidenAccessException("Attack does not belong to the specified character.");
        }
        mapDtoToEntity(attackDto, attackToUpdate);
        return attacksRepository.save(attackToUpdate);
    }

    public void deleteAttackForCharacter(UUID characterId, UUID attackId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);
        Attack attackToDelete = attacksRepository.findById(attackId)
                .orElseThrow(() -> new ForbidenAccessException("Attack not found."));
        if (!attackToDelete.getCharacter().getId().equals(characterId)) {
            throw new ForbidenAccessException("Attack does not belong to the specified character.");
        }
        attacksRepository.delete(attackToDelete);
    }


    private void mapDtoToEntity(AttackRequestDTO dto, Attack attack) {
        attack.setName(dto.name());
        attack.setType(dto.type());
        attack.setTestAttribute(dto.testAttribute());
        attack.setTestExpertise(dto.testExpertise());
        attack.setTestBonus(dto.testBonus() != null ? dto.testBonus() : 0);
        attack.setDamageDiceQuantity(dto.damageDiceQuantity());
        attack.setDamageDiceType(dto.damageDiceType());
        attack.setDamageBonus(dto.damageBonus() != null ? dto.damageBonus() : 0);
        attack.setCriticalThreshold(dto.criticalThreshold());
        attack.setCriticalMultiplier(dto.criticalMultiplier());
        attack.setRange(dto.range());
        attack.setSpecial(dto.special());
    }
}
