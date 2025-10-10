package dev.kaiserInc.AngelOfTheDices.auth;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CookieService {

    private final Duration refreshTokenDuration = Duration.ofDays(7);

    public ResponseCookie createRefreshTokenCookie(String token) {
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(refreshTokenDuration)
                .sameSite("Strict")
                .build();
    }

    public ResponseCookie createLogoutCookie(){
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();
    }
}
