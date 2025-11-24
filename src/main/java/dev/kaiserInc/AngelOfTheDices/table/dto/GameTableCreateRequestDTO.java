package dev.kaiserInc.AngelOfTheDices.table.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GameTableCreateRequestDTO(
        @NotBlank(message = "O nome da mesa não pode estar vazio.")
        @Size(min = 3, max = 200, message = "O nome da mesa deve ter entre 3 e 200 caracteres.")
        String name
) {}
