package com.vheekey.book_service.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

@Slf4j
public class JwtTokenUtil {
    private final SecretKey key;

    @Value("${api.key}")
    private String envXApiKey;


    public JwtTokenUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        log.info("Key algorithm: {}", key.getAlgorithm());
        log.info("JWT Token Util initialized with secret: {}", secret);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("JWT signature validation failed: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token: {}", e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("JWT Token validation error: {}", e.getMessage());
            return false;
        }
    }

    public boolean isXApiKeyValid(String xApiKey) {
        return xApiKey != null && xApiKey.equals(envXApiKey);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
