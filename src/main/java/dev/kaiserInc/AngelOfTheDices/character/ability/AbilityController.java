package dev.kaiserInc.AngelOfTheDices.character.ability;

import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityMapper;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.ability.dto.AbilityResponseDTO;
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
@RequestMapping("/characters/{characterId}/abilities")
public class AbilityController {

    private final AbilityService abilityService;

    @Autowired
    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @PostMapping
    public ResponseEntity<AbilityResponseDTO> addAbilityToCharacter(
            @PathVariable UUID characterId,
            @Valid @RequestBody AbilityRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Ability newAbility = abilityService.createAbilityForCharacter(characterId, userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(AbilityMapper.toResponseDTO(newAbility));
    }

    @GetMapping
    public ResponseEntity<List<AbilityResponseDTO>> getAbilities(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Set<Ability> abilities = abilityService.findAllAbilitiesByCharacter(characterId, userId);
        List<AbilityResponseDTO> dtos = abilities.stream().map(AbilityMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{abilityId}")
    public ResponseEntity<AbilityResponseDTO> getAbilityById(
            @PathVariable UUID characterId,
            @PathVariable UUID abilityId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Ability ability = abilityService.findAbilityById(characterId, abilityId, userId);

        AbilityResponseDTO responseDto = AbilityMapper.toResponseDTO(ability);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{abilityId}")
    public ResponseEntity<AbilityResponseDTO> updateAbility(
            @PathVariable UUID characterId,
            @PathVariable UUID abilityId,
            @Valid @RequestBody AbilityRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Ability updatedAbility = abilityService.updateAbilityForCharacter(characterId, abilityId, userId, requestDTO);
        return ResponseEntity.ok(AbilityMapper.toResponseDTO(updatedAbility));
    }

    @DeleteMapping("/{abilityId}")
    public ResponseEntity<Void> deleteAbility(
            @PathVariable UUID characterId,
            @PathVariable UUID abilityId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        abilityService.deleteAbilityForCharacter(characterId, abilityId, userId);
        return ResponseEntity.noContent().build();
    }
}
