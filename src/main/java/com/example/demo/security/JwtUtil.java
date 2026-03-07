package com.example.demo.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
       
  private static final String SECRET = "bXktc3VwZXItbG9uZy1hbmQtdmVyeS1zZWN1cmUtc2VjcmV0LWtleS0xMjM0NTY3ODkw";
    private static final SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

 public String generateToken(String username){

  return Jwts.builder()
          .setSubject(username)
          .setIssuedAt(new Date())
          .setExpiration(new Date(System.currentTimeMillis()+86400000))
          .signWith(key)
          .compact();
 }

 public String extractUsername(String token){

  return Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
 }
}