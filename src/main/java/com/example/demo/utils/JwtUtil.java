package main.java.com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secret = "tX+krkFo7Je+Q3cNvZ+pY0MfhMBD0pW5UzQPRP5cPZg=";

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Claims extractClaims(String token){
        return  Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, String username){
        String tokenUsername = extractClaims(token).getSubject();
        return  (username.equals(tokenUsername)&& !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return  extractClaims(token).getExpiration().before(new Date());
    }
}
