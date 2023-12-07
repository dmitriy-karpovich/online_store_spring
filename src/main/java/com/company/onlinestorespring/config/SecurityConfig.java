package com.company.onlinestorespring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private AuthenticationSuccessHandlerImpl successHandler;

    @Autowired
    public void setSuccessHandler(AuthenticationSuccessHandlerImpl successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/basket/**").authenticated()
                                .requestMatchers("/product/**").hasRole("ADMIN")
                                .requestMatchers("/user/profile").authenticated()
                                .requestMatchers("/user/registration", "/user/register",
                                                 "/user/login-page").anonymous()
                                .requestMatchers("/order/user-orders", "/order/place-order-form",
                                                 "/order/place-order").authenticated()
                                .requestMatchers("/order/active-orders", "/order/finalize-order",
                                                 "/order/refuse-order", "/order/completed-orders").hasRole("ADMIN")
                                .requestMatchers("/images/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/user/login-page")
                                .loginProcessingUrl("/authenticate")
                                .permitAll()
                                .successHandler(successHandler))
                .logout(logout ->
                        logout
                                .permitAll()
                                .logoutSuccessUrl("/main")
                                .invalidateHttpSession(true))
                .sessionManagement(session ->
                        session.invalidSessionUrl("/main"))
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied"));
        return http.build();
    }
}