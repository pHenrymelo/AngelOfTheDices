package dev.kaiserInc.AngelOfTheDices.character.item.dto;

import dev.kaiserInc.AngelOfTheDices.character.item.Item;

public final class ItemMapper {

    private ItemMapper() {}

    public static Item toEntity(ItemRequestDTO dto) {
        Item item = new Item();
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setSpaces(dto.spaces());

        return item;
    }

    public static Item updateFromDTO(ItemRequestDTO dto, Item item) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setSpaces(dto.spaces());

        return item;
    }

    public static ItemResponseDTO toResponseDTO(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getSpaces()
        );
    }
}
