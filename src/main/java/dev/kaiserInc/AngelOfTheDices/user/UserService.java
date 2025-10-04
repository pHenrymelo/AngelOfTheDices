package dev.kaiserInc.AngelOfTheDices.user;

import dev.kaiserInc.AngelOfTheDices.exception.types.DataConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
