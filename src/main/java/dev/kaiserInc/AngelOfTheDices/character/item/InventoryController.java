package dev.kaiserInc.AngelOfTheDices.character.item;

import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemMapper;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemResponseDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters/{characterId}/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> addItemToInventory(
            @PathVariable UUID characterId,
            @Valid @RequestBody ItemRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Item newItem = inventoryService.createItemForCharacter(characterId, userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemMapper.toResponseDTO(newItem));
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getInventory(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        List<Item> items = inventoryService.findAllItemsByCharacter(characterId, userId);
        List<ItemResponseDTO> dtos = items.stream().map(ItemMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> updateItemInInventory(
            @PathVariable UUID characterId,
            @PathVariable UUID itemId,
            @Valid @RequestBody ItemRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Item updatedItem = inventoryService.updateItemForCharacter(characterId, itemId, userId, requestDTO);
        return ResponseEntity.ok(ItemMapper.toResponseDTO(updatedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItemFromInventory(
            @PathVariable UUID characterId,
            @PathVariable UUID itemId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        inventoryService.deleteItemForCharacter(characterId, itemId, userId);
        return ResponseEntity.noContent().build();
    }
}
