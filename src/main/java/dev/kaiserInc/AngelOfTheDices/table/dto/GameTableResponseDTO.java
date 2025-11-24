package dev.kaiserInc.AngelOfTheDices.table.dto;

import java.util.UUID;

public record GameTableResponseDTO(
        UUID id,
        String name,
        GmResponseDTO gm
) {}
