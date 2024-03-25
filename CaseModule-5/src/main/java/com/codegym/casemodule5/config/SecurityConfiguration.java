package com.codegym.casemodule5.config;

import com.codegym.casemodule5.repository.IUserRepository;
import com.codegym.casemodule5.service.impl.UserDetailsService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {
        AuthEntryPoint.class, UserDetailsService.class,
        IUserRepository.class, AccessDeniedFilter.class
})
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private AccessDeniedFilter accessDeniedFilter;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Filter authenticationFilter() {
        return new AuthFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.exceptionHandling(exception ->
                exception.authenticationEntryPoint(authEntryPoint));
        httpSecurity.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(
                req -> req.requestMatchers("/api/auth/login",
                        "/api/auth/logout", "/api/auth/register","/api/category/**").permitAll());
//        
//        httpSecurity.authorizeHttpRequests(
//                req -> req.requestMatchers("/api/carts/**").authenticated());
//
//        httpSecurity.authorizeHttpRequests(
//                req -> req.requestMatchers("/api/users/**").hasAnyRole("ADMIN", "USER")
//        );
        httpSecurity.authorizeHttpRequests(
                req -> req.requestMatchers(HttpMethod.GET,"/api/drugs/**").permitAll()
        );
        httpSecurity.authorizeHttpRequests(
                req -> req.requestMatchers(HttpMethod.POST,"/api/drugs/**").hasAnyRole("ADMIN")
        );
        httpSecurity.authorizeHttpRequests(
                req -> req.requestMatchers(HttpMethod.DELETE,"/api/drugs/**").hasAnyRole("ADMIN")
        );

        httpSecurity.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.authenticationEntryPoint(authEntryPoint));
        httpSecurity.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.rememberMe(req -> req.tokenRepository(new InMemoryTokenRepositoryImpl()));

        httpSecurity.addFilterAfter(accessDeniedFilter, FilterSecurityInterceptor.class);

        return httpSecurity.build();
    }


}
