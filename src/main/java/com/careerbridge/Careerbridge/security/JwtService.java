package com.careerbridge.Careerbridge.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "8A6E7F4B5C2D1E9F8A7B6C5D4E3F2A1B8C7D6E5F4A3B2C1D";

    private Key getSignKey(){

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email){

        return Jwts.builder()
                   .subject(email)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                   .signWith(getSignKey())
                   .compact();
    }

    public String extractEmail(String token){

        Claims claims = Jwts.parser()
                            .verifyWith((javax.crypto.SecretKey) getSignKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
        
        return claims.getSubject();
    }
}
