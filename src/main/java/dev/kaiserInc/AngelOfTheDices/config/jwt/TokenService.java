package dev.kaiserInc.AngelOfTheDices.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {


    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.refresh.secret}")
    private String jwtRefreshSecret;

    private final long FifteenMinutesInMilisseconds = 1000 * 60 * 15;
    private final long SevenDaysInMilisseconds = 1000 * 60 * 60 * 24 * 7;

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication.getName(), FifteenMinutesInMilisseconds, jwtSecret);
    }

    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication.getName(), SevenDaysInMilisseconds, jwtRefreshSecret);
    }

    public String generateToken(String subject, long duration, String secret) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + duration);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getSubjectFromRefreshToken(String refreshToken) {
        SecretKey key = Keys.hmacShaKeyFor(jwtRefreshSecret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload();

        return claims.getSubject();
    }

    public String getSubjectFromAccessToken(String accessToken) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();

        return claims.getSubject();
    }
}
