package com.alkemy.ong.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "secret";

    //Extract the username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Extract expiration date from token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Parse jwt token into the body
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //Check if token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Generate token from user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //Settings regarding the token, as the expiration date: 24hs
    private String createToken(Map<String, Object> claims, String subject) {
        var now = LocalDateTime.now();
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date.from(now.toInstant(UTC)))
                .setExpiration(Date.from(now.plusDays(1).toInstant(UTC)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //If the user details is correct and token not expired, validate the token.
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
