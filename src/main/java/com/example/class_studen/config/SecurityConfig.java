package com.example.class_studen.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    private final PasswordEncoder passwordEncoder;


    /*@Autowired // todo:inMemoryAuthentication
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser(passwordEncoder.encode("Husanboy"))
                .password(passwordEncoder.encode("root"))
                .roles("Admin")
                .and().passwordEncoder(passwordEncoder);
    }*/

    @Autowired // todo:jdbcAuthentication
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/groups/**").permitAll()
                .requestMatchers("/marks/**").permitAll()
                .requestMatchers("/student/**").permitAll()
                .requestMatchers("/subjects/**").permitAll()
                .requestMatchers("/subTeach/**").permitAll()
                .requestMatchers("/teachers/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().build();

    }
}
