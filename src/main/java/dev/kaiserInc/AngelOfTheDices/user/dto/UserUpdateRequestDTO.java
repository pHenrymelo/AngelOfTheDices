package dev.kaiserInc.AngelOfTheDices.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
   @NotBlank(message = "Name cannot be blank")
   @Size(min = 3, message = "Name must have at least 3 characters")
   String name
) {}
