package dev.kaiserInc.AngelOfTheDices.character.note.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteRequestDTO(@NotBlank String title, String description) {}
