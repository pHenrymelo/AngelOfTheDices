package dev.kaiserInc.AngelOfTheDices.user;

import dev.kaiserInc.AngelOfTheDices.user.dto.UserCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(UserMapper.toResponseDTO(user));
    }

}
