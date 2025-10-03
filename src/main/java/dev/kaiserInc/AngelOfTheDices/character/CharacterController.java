package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterMapper;
import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterResponseDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
