package com.imsi.car.config.filter;

import java.io.IOException;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.imsi.car.config.JwtProperties;
import com.imsi.car.config.auth.PrincipalDetails;
import com.imsi.car.domain.user.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = User.builder().username("google_109973716227474992046").build();

        log.info("principal : {}", principalDetails);

        String token = jwtProperties.generateToken(user.getUsername());
        log.info("token : {}", token);
        ResponseCookie accessTokenCookie = ResponseCookie.from(jwtProperties.HEADER_AUTH, token)
                .path("/")
                // .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
        response.setHeader("Set-Cookie", accessTokenCookie.toString());
        // redirecet 할 주소
        String targetUrl = UriComponentsBuilder.fromUriString("http://carmunis.com:8080/")
                .build().toUriString();
        log.info("targetUrl : {}", targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
