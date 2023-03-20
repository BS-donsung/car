package com.imsi.car.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import com.imsi.car.config.auth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITE_LIST = {

    };
    private static final String[] AUTH_USER_LIST = {

    };
    private static final String[] AUTH_ADMIN_LIST = {

    };

    private final PrincipalOauth2UserService principalOAuth2UserService;

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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var userAuth = AuthorityAuthorizationManager
                .<RequestAuthorizationContext>hasRole("USER");
        var adminAuth = AuthorityAuthorizationManager
                .<RequestAuthorizationContext>hasRole("ADMIN");
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                        .requestMatchers(AUTH_USER_LIST).access(userAuth)
                        .requestMatchers(AUTH_ADMIN_LIST).access(adminAuth)
                        // .anyRequest().denyAll()
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login"))
                .oauth2Login(form -> form
                        .loginPage("/login")
                        .userInfoEndpoint()
                        .userService(principalOAuth2UserService));

        return http.build();
    }
}
