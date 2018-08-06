package com.example.demo.security;

import com.example.demo.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(JwtUser jwtUser) {

        Claims claims=Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("userId",String.valueOf(jwtUser.getId()));

              return  Jwts.builder()
                .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512,"youtube")
                        .compact();

    }
}
