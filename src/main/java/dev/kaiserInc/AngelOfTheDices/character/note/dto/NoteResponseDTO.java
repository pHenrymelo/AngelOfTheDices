package dev.kaiserInc.AngelOfTheDices.character.note.dto;

import java.time.Instant;
import java.util.UUID;

public record NoteResponseDTO(
        UUID id,
        String title,
        String description,
        boolean isPinned,
        Instant createdAt
) {}
