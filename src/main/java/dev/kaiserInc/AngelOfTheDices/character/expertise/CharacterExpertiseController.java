package dev.kaiserInc.AngelOfTheDices.character.expertise;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterMapper;
import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterResponseDTO;
import dev.kaiserInc.AngelOfTheDices.character.dto.SetExpertiseRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/characters/{characterId}/expertises")
public class CharacterExpertiseController {

    private final ExpertiseService expertiseService;

    @Autowired
    public CharacterExpertiseController(ExpertiseService expertiseService) {
        this.expertiseService = expertiseService;
    }

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> setExpertise(
            @PathVariable UUID characterId,
            @Valid @RequestBody SetExpertiseRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Character updatedCharacter = expertiseService.setExpertise(characterId, userId, requestDTO);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(updatedCharacter));
    }

    @GetMapping
    public ResponseEntity<Set<CharacterExpertise>> getAllExpertisesForCharacter(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Set<CharacterExpertise> expertises = expertiseService.findAllExpertisesByCharacter(characterId, userId);

        return ResponseEntity.ok(expertises);
    }

    @GetMapping("/{expertiseName}")
    public ResponseEntity<CharacterExpertise> getExpertiseByName(
            @PathVariable UUID characterId,
            @PathVariable ExpertiseName expertiseName,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        CharacterExpertise expertise = expertiseService.findExpertiseByName(characterId, userId, expertiseName);

        return ResponseEntity.ok(expertise);
    }
}
