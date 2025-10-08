package dev.kaiserInc.AngelOfTheDices.character.attack;

import dev.kaiserInc.AngelOfTheDices.character.ability.Ability;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityMapper;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackMapper;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.attack.dto.AttackResponseDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters/{characterId}/attacks")
public class AttackController {

    private final AttackService attackService;

    @Autowired
    public AttackController(AttackService attackService) {
        this.attackService = attackService;
    }

    @PostMapping
    public ResponseEntity<AttackResponseDTO> addAttackToCharacter(
            @PathVariable UUID characterId,
            @Valid @RequestBody AttackRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Attack newAttack = attackService.createAttackForCharacter(characterId, userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(AttackMapper.toResponseDTO(newAttack));
    }

    @GetMapping
    public ResponseEntity<List<AttackResponseDTO>> getAttacks(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Set<Attack> attacks = attackService.findAllAttacksByCharacter(characterId, userId);
        List<AttackResponseDTO> dtos = attacks.stream().map(AttackMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{attackId}")
    public ResponseEntity<AttackResponseDTO> getAttackById(
            @PathVariable UUID characterId,
            @PathVariable UUID attackId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Attack attack = attackService.findAttackById(characterId, attackId, userId);

        AttackResponseDTO responseDto = AttackMapper.toResponseDTO(attack);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{attackId}")
    public ResponseEntity<AttackResponseDTO> updateAttack(
            @PathVariable UUID characterId,
            @PathVariable UUID attackId,
            @Valid @RequestBody AttackRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Attack updatedAttack = attackService.updateAttackForCharacter(characterId, attackId, userId, requestDTO);
        return ResponseEntity.ok(AttackMapper.toResponseDTO(updatedAttack));
    }

    @DeleteMapping("/{attackId}")
    public ResponseEntity<Void> deleteAttack(
            @PathVariable UUID characterId,
            @PathVariable UUID attackId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        attackService.deleteAttackForCharacter(characterId, attackId, userId);
        return ResponseEntity.noContent().build();
    }

}
