package com.zwigato.service.identity.config;

import com.zwigato.service.identity.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    private final UserRepository userRepository;

    public BeanConfiguration(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Exist !!"));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
     daoAuthenticationProvider.setUserDetailsService(userDetailsService());
     return daoAuthenticationProvider;
    }
}
