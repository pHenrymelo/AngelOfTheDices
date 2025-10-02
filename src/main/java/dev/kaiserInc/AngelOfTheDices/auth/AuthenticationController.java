package dev.kaiserInc.AngelOfTheDices.auth;

import dev.kaiserInc.AngelOfTheDices.config.jwt.TokenService;
import dev.kaiserInc.AngelOfTheDices.user.dto.AuthenticateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.dto.AuthenticateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/session")
    public ResponseEntity<AuthenticateResponseDTO> login(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticateRequestDTO.email(),
                authenticateRequestDTO.password()
        );

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken(auth);

        return ResponseEntity.ok(new AuthenticateResponseDTO(token));
    }
}
