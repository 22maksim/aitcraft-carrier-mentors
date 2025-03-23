package com.home.aircraft_carrier_mentors.configuration.security.model;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtils {
    private static final String SECRET_KEY = System.getenv("SECURITY_SECRET");
    private static final Long EXPIRATION_TIME = Long.valueOf(System.getenv("EXPIRATION_TIME"));

    public static String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(EXPIRATION_TIME)))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        if (token == null || token.isEmpty()) {
            log.warn("token is null or empty");
            return null;
        }
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        if (token == null || token.isEmpty()) {
            log.warn("Username don't extract. Token is null or empty");
            return null;
        }
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        if (token == null) {
            log.warn("Token for lifetime check is null");
            return true;
        }
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        if (token == null || username == null) {
            log.warn("Token or Username is null!");
            return false;
        }
        String extractedUsername = extractClaims(token).getSubject();
        return username.equals(extractedUsername);
    }

    private static SecretKey getSigningKey() {
        byte[] encodeKey = Decoders.BASE64.decode(SECRET_KEY);
        if (encodeKey == null) {
            log.warn("Secret key is null!");
            throw new SecurityException("Secret key is null!!!");
        }
        return Keys.hmacShaKeyFor(encodeKey);
    }
}
