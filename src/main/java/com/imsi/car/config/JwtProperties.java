package com.imsi.car.config;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.imsi.car.config.auth.PrincipalDetails;
import com.imsi.car.domain.user.model.User;
import com.imsi.car.domain.user.repo.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Authentication extractAuthentiation(String token) {
        String username = extractUsername(token);
        User user = userRepo.findByUsername(username);
        log.info(user);
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
                principalDetails.getAuthorities());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasUserRole = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_USER"::equals);
        log.info("isROLE_USER? : {}" ,hasUserRole);
        return authentication;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
