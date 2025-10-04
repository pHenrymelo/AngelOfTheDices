package dev.kaiserInc.AngelOfTheDices.exception.dto;

import java.time.Instant;

public record ErrorResponseDTO(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {}
