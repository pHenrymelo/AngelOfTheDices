package dev.kaiserInc.AngelOfTheDices.user.dto;

import dev.kaiserInc.AngelOfTheDices.user.User;

public class UserMapper {

    public static User toEntity(UserCreateRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
