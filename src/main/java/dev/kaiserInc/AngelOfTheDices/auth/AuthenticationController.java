package dev.kaiserInc.AngelOfTheDices.auth;

import dev.kaiserInc.AngelOfTheDices.config.jwt.TokenService;
import dev.kaiserInc.AngelOfTheDices.user.dto.AuthenticateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.dto.AuthenticateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/session")
    public ResponseEntity<AuthenticateResponseDTO> login(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequestDTO.email(), authenticateRequestDTO.password())
        );

        String accessToken = tokenService.generateAccessToken(auth);
        String refreshToken = tokenService.generateRefreshToken(auth);
        ResponseCookie refreshTokenCookie = cookieService.createRefreshTokenCookie(refreshToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new AuthenticateResponseDTO(accessToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticateResponseDTO> refreshToken(@CookieValue(name = "refreshToken") String refreshTokenValue) {
        String userEmail = tokenService.getSubjectFromRefreshToken(refreshTokenValue);
        UserDetails userDetails = authenticationService.loadUserByUsername(userEmail);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        String newAccessToken = tokenService.generateAccessToken(auth);

        return ResponseEntity.ok(new AuthenticateResponseDTO(newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        ResponseCookie logoutCookie = cookieService.createLogoutCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, logoutCookie.toString())
                .build();
    }
}
