package com.imsi.car.config.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imsi.car.config.JwtProperties;
import com.imsi.car.config.auth.PrincipalDetails;
import com.imsi.car.domain.user.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        log.info("here is jwt login");
        Authentication authentication = null;
        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            log.info("jwt login user: {}", user);
            var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken); 

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("jwt login pr: {}", principalDetails.getUser());

        } catch (NullPointerException | BadCredentialsException e) {
            log.error("worng auth :", e);
        } catch (Exception e) {
            log.error("unexcepted error :", e);
        }

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        // TODO Auto-generated method stub
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String token = jwtProperties.generateToken(principalDetails.getUsername());
        log.info("success login, generate token : {}", token);
        ResponseCookie accessTokenCookie = ResponseCookie.from(jwtProperties.HEADER_AUTH, token)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
        response.setHeader("Set-Cookie", accessTokenCookie.toString());
    }

}
