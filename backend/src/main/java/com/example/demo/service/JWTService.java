package com.example.demo.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;

    // Generate new JWT Token upon server reboot
    // public JWTService() {
    //     try {
    //         KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
    //         SecretKey sk = keyGen.generateKey();
    //         secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    //         System.out.println("SECRET KEY BASE 64: " + secretKey);
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error generating key", e);
    //     }
    // }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "user");

        try {
            return Jwts.builder()
                .claims()
                .add(claims)
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(getSigningKey())
                .compact();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate token", e); 
        }
    }

    public boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token);
        Date expirationDate = claimsJws.getPayload().getExpiration();
        if (expirationDate.before(new Date())) {
            throw new TokenExpiredException("Token has expired");
        }
        return true;
    }
}