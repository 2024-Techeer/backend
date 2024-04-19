package com.example.Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.mongodb.client.model.Filters.and;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration //해당 클래스가 Spring 구성 클래스임을 나타내며, 빈 정의를 생성하기 위한 @Bean 메소드를 포함할 수 있습니다.
public class SecurityConfig  {//WebSecurityConfigurerAdapter 클래스가 Spring Security 5.7.x 버전 이후로 더 이상 권장되지 않음.
    // SecurityFilterChain 빈을 직접 정의하는 새로운 방식을 사용하는 것이 권장
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpReqeuests -> authorizeHttpReqeuests
                        .requestMatchers("/api/v1/hello","api/v1/register").permitAll()
                        .anyRequest().authenticated()
                );



        return http.build();
    }

}
//@Configuration없이는 @Bean 메소드로 정의된 SecurityFilterChain 빈이 Spring 컨테이너에 의해 실제로 등록되지 않을 수 있습니다.
//CORS는 웹 페이지가 다른 도메인의 리소스에 접근할 수 있도록 허용하는 보안 메커니즘입니다
//CSRF는 공격자가 사용자의 지식 없이 사용자가 이미 인증된 웹 애플리케이션에서 원치 않는 작업을 수행하도록 하는 공격 유형
