package com.juchan.board.springboardjpa.config.security;

import com.juchan.board.springboardjpa.api.member.service.MemberDetailService;
import com.juchan.board.springboardjpa.config.filter.MemberAuthenticatorProvider;
import com.juchan.board.springboardjpa.config.handler.LoginFailCustomHandler;
import com.juchan.board.springboardjpa.config.handler.LoginSuccessCustomHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginSuccessCustomHandler successHandler;
    private final LoginFailCustomHandler failureHandler;

    private final MemberDetailService memberDetailService;

    private final MemberAuthenticatorProvider memberAuthenticatorProvider;


    // in memory 방식으로 인증 처리를 진행 하기 위해 기존엔 Override 하여 구현했지만
    // Spring Security 5.7.0 버전부터는 AuthenticationManagerBuilder를 직접 생성하여
    // AuthenticationManager를 생성해야 한다.
    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(memberAuthenticatorProvider);
    }
    //static 이하 동적 파일 경로 Security 비활성화 적용
    @Bean
    public WebSecurityCustomizer configure(){
        //2024.03.29 favicon.ico 찾지 못하는 이슈료 URL 호출을 2번하게됨 -> 해당 부분 예외처리로 해결.
        return (web) -> web.ignoring()
                .requestMatchers("/static/**", "/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                //url 접근 권한 설정.
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/article/**").hasRole("USER")
                        .requestMatchers("/article/comment/**").hasRole("USER")

                        .anyRequest().authenticated()
                        //.requestMatchers("/article/**").hasRole("USER")

                )
                //cors 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                //인증 구현 방식은 2가지
                /**
                 * 1) formLogin을 통해서 구현하게되면 기본적으로 UsernamePasswordAuthenticationFilter를 통해서 provider 호출 후 인증 처리
                 *
                 * 2) UsernamePasswordAuthenticationFilter를 상속받은 customFilter 구현하여 직접 인증 처리 로직 구현.
                 *
                 */
                //자체적으로 선언 시 기본 UsernamePasswordAuthenticationFilter를 통해서 폼 기반 로그인 처리.
                .formLogin(login -> login
                        .loginPage("/member/login")	                    // [A] 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/member/login-processing")	// [B] submit 받을 url
                        .usernameParameter("loginId")	                // [C] submit할 아이디
                        .passwordParameter("password")	                // [D] submit할 비밀번호
                        .successHandler(successHandler)
                        //이친구를 설정하게 되면 위에 successHandler를 안탐   -> successHandler가 구현하면서 추가 작업을 더 할 수 있어서 선택.
                        //.defaultSuccessUrl("/main/index")
                        .failureHandler(failureHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/member/login")
                        .deleteCookies("JSESSIONID") // 로그아웃 후 쿠키 삭제
                )
                .csrf().disable()


        ;

        return http.build();
    }

    //CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Content-Type", "Authorization", "X-XSRF-token"));
        configuration.setAllowCredentials(false);
        //3600초
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
