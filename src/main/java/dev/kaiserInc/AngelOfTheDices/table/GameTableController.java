package dev.kaiserInc.AngelOfTheDices.table;

import dev.kaiserInc.AngelOfTheDices.table.dto.GameTableCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.table.dto.GameTableMapper;
import dev.kaiserInc.AngelOfTheDices.table.dto.GameTableResponseDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class GameTableController {

    private final GameTableService gameTableService;

    @Autowired
    public GameTableController(GameTableService gameTableService) {
        this.gameTableService = gameTableService;
    }

    @PostMapping
    public ResponseEntity<GameTableResponseDTO> createTable(
            @Valid @RequestBody GameTableCreateRequestDTO dto,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        GameTable createdTable = gameTableService.createTable(dto, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GameTableMapper.toResponseDTO(createdTable));
    }

    @GetMapping
    public ResponseEntity<List<GameTableResponseDTO>> getAllTables() {
        List<GameTable> tables = gameTableService.findAllTables();
        List<GameTableResponseDTO> dtos = tables.stream()
                .map(GameTableMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
