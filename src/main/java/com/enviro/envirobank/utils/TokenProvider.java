package com.enviro.envirobank.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")

    private String jwtSecretCode;

//Generating the JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date expiryDate = new Date(System.currentTimeMillis()+1000*60*60*10);
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key()).compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretCode));
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        }
        catch (ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e){
            throw new RuntimeException(e.getMessage());
        }
    }

//    Fetching username from the Token

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key())
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public  String resetPasswordToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(key()).compact();
    }
}
