package dev.kaiserInc.AngelOfTheDices.character.item;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.CharactersRepository;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemMapper;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.character.item.dto.ItemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class InventoryService {

    private final ItemsRepository itemsRepository;
    private final CharacterService characterService;
    private final CharactersRepository charactersRepository;

    @Autowired
    public InventoryService(ItemsRepository itemsRepository, CharacterService characterService, CharactersRepository charactersRepository) {
        this.itemsRepository = itemsRepository;
        this.characterService = characterService;
        this.charactersRepository = charactersRepository;
    }

    public Item createItemForCharacter(UUID characterId, UUID userId, ItemRequestDTO itemDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Item newItem = ItemMapper.toEntity(itemDto);
        newItem.setCharacter(character);

        return itemsRepository.save(newItem);
    }

    public Set<Item> findAllItemsByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getInventory();
    }

    public Item updateItemForCharacter(UUID characterId, UUID itemId, UUID userId, ItemRequestDTO itemDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Item itemToUpdate = itemsRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        if (!itemToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Item does not belong to the specified character.");
        }

        ItemMapper.updateEntityFromDTO(itemDto, itemToUpdate);

        return itemsRepository.save(itemToUpdate);
    }

    public void deleteItemForCharacter(UUID characterId, UUID itemId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Item itemToDelete = character.getInventory().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item with id " + itemId + " not found in this character's inventory."));

        character.getInventory().remove(itemToDelete);

        charactersRepository.save(character);
    }
}
