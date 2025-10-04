package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.dto.*;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createCharacter(
            @Valid @RequestBody CharacterCreateRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Character createdCharacter = characterService.createCharacter(requestDTO, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(CharacterMapper.toResponseDTO(createdCharacter));
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getAllForUser(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        List<Character> characters = characterService.findAllByUser(userId);

        List<CharacterResponseDTO> responseDTOs = characters.stream()
                .map(CharacterMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterResponseDTO> getById(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        UUID userId = user.getId();

        Character character = characterService.findByIdAndUser(characterId, userId);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(character));
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(
            @PathVariable UUID characterId,
            @Valid @RequestBody CharacterUpdateDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Character updatedCharacter = characterService.updateCharacter(characterId, userId, requestDTO);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(updatedCharacter));
    }

    @PatchMapping("/{characterId}/status")
    public ResponseEntity<CharacterResponseDTO> patchCharacterStatus(
            @PathVariable UUID characterId,
            @Valid @RequestBody CharacterStatusUpdateDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Character updatedCharacter = characterService.patchCharacterStatus(characterId, userId, requestDTO);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(updatedCharacter));
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> deleteCharacter(
            @PathVariable UUID characterId,
            Authentication authentication
    ) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        characterService.deleteCharacter(characterId, userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{characterId}/expertises")
    public ResponseEntity<CharacterResponseDTO> setExpertise(
            @PathVariable UUID characterId,
            @Valid @RequestBody SetExpertiseRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Character updatedCharacter = characterService.setExpertise(characterId, userId, requestDTO);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(updatedCharacter));
    }
}
