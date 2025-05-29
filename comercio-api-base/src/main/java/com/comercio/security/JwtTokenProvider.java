package com.comercio.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Base64;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    private String secretKey = "123456789012345678901234567890";
    private long validityInMs = 3600000; // 1 hora

    private SecretKey key;

    @PostConstruct
    protected void init() {
        byte[] decodedKey = Base64.getEncoder().encode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String createToken(String username, String role, Long usuarioId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("rol", role);
        claims.put("id", usuarioId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getRole(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("rol");
    }
    
    public Long getUsuarioId(String token) {
        Object id = Jwts.parserBuilder().setSigningKey(key).build()
                         .parseClaimsJws(token).getBody().get("id");
        return Long.parseLong(id.toString());
    }
}
