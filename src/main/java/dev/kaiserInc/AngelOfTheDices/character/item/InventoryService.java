package dev.kaiserInc.AngelOfTheDices.character.item;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemMapper;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbidenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {

    private final ItemsRepository itemsRepository;
    private final CharacterService characterService;

    @Autowired
    public InventoryService(CharacterService characterService, ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
        this.characterService = characterService;
    }

    public Item createItemForCharacter(UUID characterId, UUID userId, ItemRequestDTO itemDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Item newItem = ItemMapper.toEntity(itemDto);
        newItem.setCharacter(character);

        return itemsRepository.save(newItem);
    }

    public List<Item> findAllItemsByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getInventory();
    }

    public Item updateItemForCharacter(UUID characterId, UUID itemId, UUID userId, ItemRequestDTO itemDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Item itemToUpdate = itemsRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        if (!itemToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbidenAccessException("Item does not belong to the specified character.");
        }

        ItemMapper.updateFromDTO(itemDto, itemToUpdate);

        return itemsRepository.save(itemToUpdate);
    }

    public void deleteItemForCharacter(UUID characterId, UUID itemId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Item itemToDelete = itemsRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        if (!itemToDelete.getCharacter().getId().equals(characterId)) {
            throw new ForbidenAccessException("Item does not belong to the specified character.");
        }

        itemsRepository.delete(itemToDelete);
    }
}
