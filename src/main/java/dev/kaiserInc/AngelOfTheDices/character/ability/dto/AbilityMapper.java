package dev.kaiserInc.AngelOfTheDices.character.ability.dto;

import dev.kaiserInc.AngelOfTheDices.character.ability.Ability;

public final class AbilityMapper {
    private AbilityMapper() {}

    public static Ability toEntity(AbilityRequestDTO dto) {
        Ability ability = new Ability();
        ability.setName(dto.name());
        ability.setDescription(dto.description());
        ability.setType(dto.type());
        return ability;
    }

    public static AbilityResponseDTO toResponseDTO(Ability ability) {
        return new AbilityResponseDTO(
                ability.getId(),
                ability.getName(),
                ability.getDescription(),
                ability.getType()
        );
    }
}
