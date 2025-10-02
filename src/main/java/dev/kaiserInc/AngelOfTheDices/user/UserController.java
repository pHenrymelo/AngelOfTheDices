package dev.kaiserInc.AngelOfTheDices.user;

import dev.kaiserInc.AngelOfTheDices.user.dto.UserCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserCreateRequestDTO requestDTO) {

        User newUser = UserMapper.toEntity(requestDTO);

        User createdUser = userService.createUser(newUser);

        UserResponseDTO responseDTO = UserMapper.toResponseDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


}
