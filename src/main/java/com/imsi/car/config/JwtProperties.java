package com.imsi.car.config;

import java.security.Key;
import java.util.Date;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.imsi.car.config.auth.PrincipalDetails;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Component
@Log4j2
public class JwtProperties {
    public final int EXPIRATION = 3600 * 1000 * 60; // 1시간
    public final String SECRET = "bananaisgogogogo4321sbababafv4134na";
    public final String HEADER_AUTH = "Authorization";

    public final UserRepo userRepo;



    public String generateToken(String userName) {
        User user = userRepo.findByUsername(userName);
        Claims claims = Jwts.claims();
        claims.put("role", user.getRole());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        User user = userRepo.findByUsername(getUsername(token));
        if (claims.get("role") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        // UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
