package dev.kaiserInc.AngelOfTheDices.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequestDTO(
   @NotBlank(message = "Name cannot be blank")
   String name
) {}
