package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Value("${app.admin.username}")
    private String adminUser;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                // 1. Libera a LEITURA dos projetos e artigos para o público (Vercel/Local)
                .requestMatchers(HttpMethod.GET, "/admin/api/projects", "/admin/api/articles").permitAll()
                .requestMatchers(HttpMethod.GET, "/skills", "/api/gallery/**", "/health").permitAll()
                
                // 2. Exige AUTENTICAÇÃO para POST, PUT e DELETE (Criação e Edição)
                .requestMatchers(HttpMethod.POST, "/admin/api/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/admin/api/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/admin/api/**").authenticated()
                
                // 3. Protege as páginas HTML do Painel Admin
                .requestMatchers("/admin/**").authenticated()
                
                // 4. Libera o restante (CSS, JS, Index)
                .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/images/**", "/api/**").permitAll()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/admin/index.html", true)
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // URLs permitidas para acessar sua API
        configuration.setAllowedOrigins(Arrays.asList(
            "http://192.168.0.13:3000", 
            "http://davidson.dev.br", 
            "https://davidson.dev.br",
            "https://portfolio-olive-rho.vercel.app"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
            .username(adminUser)
            .password(passwordEncoder().encode(adminPassword))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}