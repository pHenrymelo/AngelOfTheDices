package dev.kaiserInc.AngelOfTheDices.attack;

import dev.kaiserInc.AngelOfTheDices.attack.dto.AttackResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters/{characterId}/attack")
public class AttackController {

    private final CharacterService characterService;

    @Autowired
    public AttackController(CharacterService characterService) {
        this.characterService = characterService;
    }

    private AttackResponseDTO toAttackResponseDTO(Attack attack) {
        return new AttackResponseDTO(
                attack.getId(),
                attack.getName(),
                attack.getType(),
                attack.getTestAttribute(),
                attack.getTestExpertise(),
                attack.getTestBonus(),
                attack.getDamageDiceQuantity(),
                attack.getDamageDiceType(),
                attack.getDamageBonus(),
                attack.getCriticalThreshold(),
                attack.getCriticalMultiplier(),
                attack.getRange(),
                attack.getSpecial());
    }
}
