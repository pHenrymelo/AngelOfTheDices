package dev.kaiserInc.AngelOfTheDices.character.ritual.dto;

import dev.kaiserInc.AngelOfTheDices.character.ritual.Ritual;

public final class RitualMapper {
    private RitualMapper() {}

    public static Ritual toEntity(RitualRequestDTO dto) {
        Ritual ritual = new Ritual();
        ritual.setName(dto.name());
        ritual.setElement(dto.element());
        ritual.setCircle(dto.circle());
        ritual.setExecution(dto.execution());
        ritual.setRange(dto.range());
        ritual.setTarget(dto.target());
        ritual.setDuration(dto.duration());
        ritual.setResistance(dto.resistance());
        ritual.setDescription(dto.description());
        return ritual;
    }

    public static void updateEntityFromDTO(RitualRequestDTO dto, Ritual ritual) {
        ritual.setName(dto.name());
        ritual.setElement(dto.element());
        ritual.setCircle(dto.circle());
        ritual.setExecution(dto.execution());
        ritual.setRange(dto.range());
        ritual.setTarget(dto.target());
        ritual.setDuration(dto.duration());
        ritual.setResistance(dto.resistance());
        ritual.setDescription(dto.description());
    }

    public static RitualResponseDTO toResponseDTO(Ritual ritual) {
        return new RitualResponseDTO(
                ritual.getId(),
                ritual.getName(),
                ritual.getElement(),
                ritual.getCircle(),
                ritual.getExecution(),
                ritual.getRange(),
                ritual.getTarget(),
                ritual.getDuration(),
                ritual.getResistance(),
                ritual.getDescription()
        );
    }
}
