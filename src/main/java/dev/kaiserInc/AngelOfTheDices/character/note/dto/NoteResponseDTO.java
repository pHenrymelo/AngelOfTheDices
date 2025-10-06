package dev.kaiserInc.AngelOfTheDices.character.note.dto;

import java.util.UUID;

public record NoteResponseDTO(UUID id, String title, String description) {}
