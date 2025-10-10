package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.dto.*;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Page<CharacterResponseDTO>> getAllForUser(Authentication authentication, Pageable pageable) {
        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Page<Character> characterPage = characterService.findAllByUser(userId, pageable);

        Page<CharacterResponseDTO> responseDTOPage = characterPage.map(CharacterMapper::toResponseDTO);

        return ResponseEntity.ok(responseDTOPage);
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterResponseDTO> getById(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        UUID userId = user.getId();

        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(character));
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(
            @PathVariable UUID characterId,
            @Valid @RequestBody CharacterUpdateRequestDTO requestDTO,
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

    @PostMapping("/{characterId}/portrait")
    public ResponseEntity<CharacterResponseDTO> uploadPortrait(
            @PathVariable UUID characterId,
            @RequestParam("file")MultipartFile file,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Character updatedCharacter = characterService.setCharacterPortrait(characterId, userId, file);

        return ResponseEntity.ok(CharacterMapper.toResponseDTO(updatedCharacter));
    }
}
