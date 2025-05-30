package br.com.fiap.ponabri.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService dadosUsuariosCadastrados (){

        UserDetails usuario1 = User.builder().username("rob@email.com").password("{noop}rob123").build();

        UserDetails usuario2 = User.builder().username("talyta@email.com").password("{noop}talyta123").build();

        return new InMemoryUserDetailsManager(usuario1,usuario2);

    }
}