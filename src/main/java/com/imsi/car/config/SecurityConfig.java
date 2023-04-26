package com.imsi.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imsi.car.config.auth.PrincipalDetailsService;
import com.imsi.car.config.auth.PrincipalOauth2UserService;
import com.imsi.car.config.filter.JwtAuthenticationFilter;
import com.imsi.car.config.filter.JwtAuthorizationFilter;
import com.imsi.car.config.filter.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITE_LIST = {

    };
    private static final String[] AUTH_USER_LIST = {
            "/user/hihi"
    };
    private static final String[] AUTH_ADMIN_LIST = {

    };

    private final PrincipalOauth2UserService principalOAuth2UserService;
    private final PrincipalDetailsService principalDetailsService;
    private final OAuth2SuccessHandler successHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        String role = "ROLE_ADMIN > ROLE_USER";
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy(role);
        return r;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtProperties jwtProperties) throws Exception {
        var userAuth = AuthorityAuthorizationManager
                .<RequestAuthorizationContext>hasRole("USER");
        var adminAuth = AuthorityAuthorizationManager
                .<RequestAuthorizationContext>hasRole("ADMIN");

        return http.csrf().disable().cors().and()
                .authenticationManager(authenticationManager(http))
                .addFilterBefore(new JwtAuthorizationFilter(jwtProperties),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(jwtProperties, authenticationManager(http)),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                        .requestMatchers(AUTH_USER_LIST).access(userAuth)
                        .requestMatchers(AUTH_ADMIN_LIST).access(adminAuth)
                        // .anyRequest().denyAll()
                        .anyRequest().permitAll())
                .formLogin().disable()
                .oauth2Login(authorize -> authorize
                        // .loginPage("/")
                        .successHandler(successHandler)
                        .userInfoEndpoint().userService(principalOAuth2UserService)
                        .and()
                        
                )
                .logout(form -> form
                        .deleteCookies(jwtProperties.HEADER_AUTH))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(principalDetailsService);
        return authenticationManagerBuilder.build();
    }
}
