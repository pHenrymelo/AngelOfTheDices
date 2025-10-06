package dev.kaiserInc.AngelOfTheDices.item.dto;

import java.util.UUID;

public record ItemResponseDTO(
        UUID id,
        String name,
        String description,
        int category,
        int spaces
) {}
