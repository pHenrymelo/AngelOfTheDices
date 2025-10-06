package dev.kaiserInc.AngelOfTheDices.user;

import dev.kaiserInc.AngelOfTheDices.exception.types.DataConflictException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.user.dto.UserMapper;
import dev.kaiserInc.AngelOfTheDices.user.dto.UserUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DataConflictException("Email already in use");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return usersRepository.save(user);
    }

    public User updateUser(UUID userId, UserUpdateRequestDTO dto) {
        User userToUpdate = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserMapper.updateEntityFromDTO(dto, userToUpdate);

        return usersRepository.save(userToUpdate);
    }
}
