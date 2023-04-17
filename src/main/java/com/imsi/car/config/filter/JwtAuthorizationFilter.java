package com.imsi.car.config.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imsi.car.config.JwtProperties;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter{

    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = resolveToken(req);
        log.info("filter : {}",request.getRequestURL().toString());
        log.info("token : {}",token);
        if(token!=null){
            Authentication authentication = jwtProperties.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication );
            req.setAttribute("username", jwtProperties.getUsername(token));
        }
        

        filterChain.doFilter(req, res);
    }

    private String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        log.info("token : {}",token);

        String result = null;
        try{
            for(Cookie c : request.getCookies()){
                if (c.getName().equals((jwtProperties.HEADER_AUTH))){
                    log.info("cookie : {}",c.getValue());
                    result = c.getValue();
                }
            }
        }catch (Exception e){
            log.error(e);
        }
        return result;
    }
    
    
}
