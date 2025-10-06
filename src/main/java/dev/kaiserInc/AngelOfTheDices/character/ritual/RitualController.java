package dev.kaiserInc.AngelOfTheDices.character.ritual;

import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualMapper;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualResponseDTO;
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
@RequestMapping("/characters/{characterId}/rituals")
public class RitualController {

    private final RitualService ritualService;

    @Autowired
    public RitualController(RitualService ritualService) {
        this.ritualService = ritualService;
    }

    @PostMapping
    public ResponseEntity<RitualResponseDTO> addRitualToCharacter(
            @PathVariable UUID characterId,
            @Valid @RequestBody RitualRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Ritual newRitual = ritualService.createRitualForCharacter(characterId, userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(RitualMapper.toResponseDTO(newRitual));
    }

    @GetMapping
    public ResponseEntity<List<RitualResponseDTO>> getRituals(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        List<Ritual> rituals = ritualService.findAllRitualsByCharacter(characterId, userId);
        List<RitualResponseDTO> dtos = rituals.stream().map(RitualMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{ritualId}")
    public ResponseEntity<RitualResponseDTO> getRitualById(
            @PathVariable UUID characterId,
            @PathVariable UUID ritualId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Ritual ritual = ritualService.findRitualById(characterId, ritualId, userId);

        RitualResponseDTO responseDto = RitualMapper.toResponseDTO(ritual);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{ritualId}")
    public ResponseEntity<RitualResponseDTO> updateRitual(
            @PathVariable UUID characterId,
            @PathVariable UUID ritualId,
            @Valid @RequestBody RitualRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        Ritual updatedRitual = ritualService.updateRitualForCharacter(characterId, ritualId, userId, requestDTO);
        return ResponseEntity.ok(RitualMapper.toResponseDTO(updatedRitual));
    }

    @DeleteMapping("/{ritualId}")
    public ResponseEntity<Void> deleteRitual(
            @PathVariable UUID characterId,
            @PathVariable UUID ritualId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        ritualService.deleteRitualForCharacter(characterId, ritualId, userId);
        return ResponseEntity.noContent().build();
    }
}
