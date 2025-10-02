package dev.kaiserInc.AngelOfTheDices.auth;

import dev.kaiserInc.AngelOfTheDices.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
