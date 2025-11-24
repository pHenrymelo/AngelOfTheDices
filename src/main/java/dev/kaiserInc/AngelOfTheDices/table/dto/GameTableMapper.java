package dev.kaiserInc.AngelOfTheDices.table.dto;

import dev.kaiserInc.AngelOfTheDices.table.GameTable;
import dev.kaiserInc.AngelOfTheDices.user.User;

public final class GameTableMapper {

    private GameTableMapper() {}

    public static GameTable toEntity(GameTableCreateRequestDTO dto) {
        GameTable table = new GameTable();
        table.setName(dto.name());
        return table;
    }

    public static GmResponseDTO toGmDTO(User gm) {
        return new GmResponseDTO(gm.getId(), gm.getName());
    }

    public static GameTableResponseDTO toResponseDTO(GameTable table) {
        return new GameTableResponseDTO(
                table.getId(),
                table.getName(),
                toGmDTO(table.getGm())
        );
    }
}
