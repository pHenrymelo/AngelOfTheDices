package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.item.Item;
import dev.kaiserInc.AngelOfTheDices.item.dto.ItemRequestDTO;
import dev.kaiserInc.AngelOfTheDices.item.dto.ItemResponseDTO;
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
@RequestMapping("/characters/{characterId}/inventory")
public class InventoryController {

    @Autowired
    private CharacterService characterService;

    private ItemResponseDTO toItemResponseDTO(Item item) {
        return new ItemResponseDTO(item.getId(), item.getName(), item.getDescription(), item.getCategory(), item.getSpaces());
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> addItemToInventory(
            @PathVariable UUID characterId,
            @Valid @RequestBody ItemRequestDTO requestDTO,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        Item newItem = characterService.createItemForCharacter(characterId, userId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(toItemResponseDTO(newItem));
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getInventory(
            @PathVariable UUID characterId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();

        List<Item> items = characterService.findAllItemsByCharacter(characterId, userId);
        List<ItemResponseDTO> dtos = items.stream().map(this::toItemResponseDTO).collect(Collectors.toList());
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
        Item updatedItem = characterService.updateItemForCharacter(characterId, itemId, userId, requestDTO);
        return ResponseEntity.ok(toItemResponseDTO(updatedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItemFromInventory(
            @PathVariable UUID characterId,
            @PathVariable UUID itemId,
            Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();
        UUID userId = userPrincipal.getId();
        characterService.deleteItemForCharacter(characterId, itemId, userId);
        return ResponseEntity.noContent().build();
    }
}
