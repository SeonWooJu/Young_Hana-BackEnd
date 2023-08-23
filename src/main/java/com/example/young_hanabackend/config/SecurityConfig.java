package com.example.young_hanabackend.config;

import com.example.young_hanabackend.security.util.CustomJwtFilter;
import com.example.young_hanabackend.security.util.JwtToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    JwtToken jwtToken;
    CustomJwtFilter customJwtFilter;

    @Autowired
    public SecurityConfig(JwtToken jwtToken, CustomJwtFilter customJwtFilter) {
        this.jwtToken = jwtToken;
        this.customJwtFilter = customJwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-----security config check-----");

        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())

                /** 세션 사용하지 않음 **/
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                /** HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정 **/
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/api/account/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated())

                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

}
