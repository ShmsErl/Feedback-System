package com.tobeto.feedback_system.config;

import com.tobeto.feedback_system.models.concretes.Role;
import com.tobeto.feedback_system.security.filter.JwtAuthenticationFilter;
import com.tobeto.feedback_system.services.concretes.CustomUserDetailsService;
import com.tobeto.feedback_system.services.concretes.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    private static final String[] WHITE_LIST_URL = {
            "/api/signin",
            "/api/signup",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/**"
    };


    private static final String[] POST_ADMIN_URL = {
            "/api/users/**",
            "/api/categories/**"


    };

    private static final String[] POST_USER_ADMIN_URL = {
            "/api/feedbacks/**",


    };

    private static final String[] PUT_USER_ADMIN_URL = {
            "/api/users",


    };
    private static final String[] PUT_ADMIN_URL = {
            "/api/feedbacks/**",
            "/api/categories/**",
            "/api/users/**",


    };

    private static final String[] GET_ADMIN_URL = {
            "/api/feedbacks/**",
            "/api/categories/**",
            "/api/users/**",


    };
    private static final String[] GET_USER_ADMIN_URL = {
            "/api/users/{id}",
            "/api/feedbacks/{id}",
            "/api/categories",
            "/api/categories/{id}",



    };


    private static final String[] DELETE_ADMIN_USER_URL = {
            "/api/feedbacks/{id}",




    };
    private static final String[] DELETE_ADMIN_URL = {
            "/api/feedbacks/**",
            "/api/categories/**",
            "/api/users/**",

    };

    private static final String[] PATCH_ADMIN_URL = {
            "/api/feedbacks/**"


    };




    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(HttpMethod.GET,GET_USER_ADMIN_URL).hasAnyRole(Role.USER.getRoleName(), Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.GET,GET_ADMIN_URL).hasRole(Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.POST,POST_USER_ADMIN_URL).hasAnyRole(Role.USER.getRoleName(), Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.POST,POST_ADMIN_URL).hasRole(Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.PUT,PUT_USER_ADMIN_URL).hasAnyRole(Role.USER.getRoleName(), Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.PUT,PUT_ADMIN_URL).hasRole(Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.PATCH,PATCH_ADMIN_URL).hasRole(Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.DELETE,DELETE_ADMIN_USER_URL).hasAnyRole(Role.USER.getRoleName(), Role.ADMIN.getRoleName())
                        .requestMatchers(HttpMethod.DELETE,DELETE_ADMIN_URL).hasRole(Role.ADMIN.getRoleName())


                        .anyRequest().authenticated()

                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }
}
