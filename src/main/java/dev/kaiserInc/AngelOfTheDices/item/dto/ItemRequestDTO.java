package dev.kaiserInc.AngelOfTheDices.item.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
        @NotBlank String name,
        String description,
        @NotNull @Min(0) Integer category,
        @NotNull @Min(0) Integer spaces
) {}
