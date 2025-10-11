package dev.kaiserInc.AngelOfTheDices.character.note.dto;

import jakarta.validation.constraints.NotNull;

public record NotePinRequestDTO(@NotNull Boolean isPinned) {}
