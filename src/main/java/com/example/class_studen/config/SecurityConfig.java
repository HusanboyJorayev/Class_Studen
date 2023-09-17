package com.example.class_studen.config;


import com.example.class_studen.security.JwtSecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtSecurityFilter jwtSecurityFilter;
    //private final DataSource dataSource;

    //private final PasswordEncoder passwordEncoder;


    // todo:inMemoryAuthentication
    /*@Autowired
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser(passwordEncoder.encode("Husanboy"))
                .password(passwordEncoder.encode("root"))
                .roles("Admin")
                .and().passwordEncoder(passwordEncoder);
    }*/

    // todo:jdbcAuthentication
    /*@Autowired
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                "/groups/**",
                "/marks/**",
                "/student/**",
                "/subjects/**",
                "/subTeach/**",
                "/teachers/**",
                "/user/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
