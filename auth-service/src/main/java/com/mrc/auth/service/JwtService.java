package com.mrc.auth.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mrc.auth.entity.Role;
import com.mrc.auth.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Generate JWT Token
     */
    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles",
                        user.getRoles()
                                .stream()
                                .map(Role::getRoleName)
                                .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Validate Token
     */
    public boolean validateToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception ex) {

            return false;
        }
    }

    /**
     * Extract Username
     */
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    /**
     * Extract Expiry
     */
    public Date extractExpiration(String token) {

        return extractClaims(token).getExpiration();
    }

    /**
     * Common method to parse JWT
     */
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Signing Key
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

}