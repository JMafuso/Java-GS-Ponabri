package br.com.fiap.ponabri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import br.com.fiap.ponabri.security.JwtConfig;
import br.com.fiap.ponabri.security.CustomUserDetailsService;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtConfig jwtConfig;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtConfig jwtConfig, CustomUserDetailsService customUserDetailsService) {
        this.jwtConfig = jwtConfig;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] keyBytes = jwtConfig.getSecret().getBytes();
        return NimbusJwtDecoder.withSecretKey(new javax.crypto.spec.SecretKeySpec(keyBytes, "HmacSHA256")).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/", "/index.html", "/frontend/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/swagger-ui/index.html", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            // Apply OAuth2 resource server JWT only to secured endpoints, exclude Swagger UI
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(new org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter())
                )
            );

        http.exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                response.setContentType("application/json");
                response.setStatus(401);
                response.getOutputStream().println("{ \"error\": \"Unauthorized\" }");
            });

        return http.build();
    }
}
