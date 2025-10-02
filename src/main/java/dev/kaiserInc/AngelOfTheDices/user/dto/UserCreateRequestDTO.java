package dev.kaiserInc.AngelOfTheDices.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(
        @NotBlank(message = "Name must be provided")
        String name,

        @NotBlank(message = "Email must be provided")
        @Email(message = "The provided email is not valid")
        String email,

        @NotBlank(message = "Password must be provided")
        @Size(min = 8, message = "Password must be at last 8 characters long")
        String password
) {}
