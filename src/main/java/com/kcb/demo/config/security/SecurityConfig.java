package com.kcb.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/h2-console/**","/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .headers(h -> h.frameOptions(f -> f.sameOrigin())); // allow H2 console
        return http.build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.withUsername("user").password("{noop}password").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
