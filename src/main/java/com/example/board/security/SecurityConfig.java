package com.example.board.security;

import com.example.board.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${spring.jwt.secret}")
  private String secretKey;
  private final UserService userService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
    return httpSecurity
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // httpBasic, csrf 공격 방지 기능 사용하지 않음
        .httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        // 페이지 권한 설정
        .authorizeHttpRequests(
            requests -> requests
//        .requestMatchers("/users/signup", "/users/signin", "/board/posts/{post_id}").permitAll() // join, login은 언제나 가능
//        .requestMatchers(HttpMethod.GET, "/board/posts/**").permitAll()
//        .requestMatchers(
//            AntPathRequestMatcher.antMatcher("/"),
//            AntPathRequestMatcher.antMatcher("/error")
//            new AntPathRequestMatcher("/"),
//            new AntPathRequestMatcher("/error"),
//            new AntPathRequestMatcher("/users/signup"),
//            new AntPathRequestMatcher("/users/signup/form"),
//            new AntPathRequestMatcher("/users/signin"),
//            new AntPathRequestMatcher("/users/signin/form"),
//            new AntPathRequestMatcher("/board/posts/{post_id}"),
//            new AntPathRequestMatcher("/board/comments/{comment_id}"),
//            new MvcRequestMatcher(introspector, "/error"),
//            new MvcRequestMatcher(introspector, "/users/**")
//        )
                .anyRequest()
                .permitAll()
//        .requestMatchers(HttpMethod.POST, "/**").authenticated() // POST 요청은 일단 다 막음
//        .requestMatchers("/**").authenticated()) // 접근 다 막음
//        .anyRequest()
//        .authenticated()
        )
//        // 토큰 필터링
//        // UserNamePasswordAuthenticationFilter적용하기 전에 JWTTokenFilter를 적용 하라는 뜻
//        .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
        .build();
  }

}
