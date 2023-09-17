package com.example.class_studen.config;

import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

   /* @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;
    @Value(value = "${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws InstantiationException, IllegalAccessException {
        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setDriver(Driver.class.newInstance());

        return source;

    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
