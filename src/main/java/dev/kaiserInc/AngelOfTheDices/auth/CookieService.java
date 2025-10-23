package dev.kaiserInc.AngelOfTheDices.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CookieService {

    private final Duration refreshTokenDuration = Duration.ofDays(7);


    @Value("${jwt.refresh.cookie-name}")
    private String refreshTokenCookieName;

    @Value("${cookie.secure}")
    private boolean cookieSecure;

    @Value("${cookie.same-site}")
    private String sameSitePolicy;

    public ResponseCookie createRefreshTokenCookie(String token) {
        return ResponseCookie.from(refreshTokenCookieName, token)
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/")
                .maxAge(refreshTokenDuration)
                .sameSite(sameSitePolicy)
                .build();
    }

    public ResponseCookie createLogoutCookie(){
        return ResponseCookie.from(refreshTokenCookieName, "")
                .httpOnly(true)
                .secure(cookieSecure)
                .path("/")
                .maxAge(0)
                .sameSite(sameSitePolicy)
                .build();
    }
}
