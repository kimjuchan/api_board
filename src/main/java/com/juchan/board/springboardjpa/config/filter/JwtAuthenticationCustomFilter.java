/*
package com.juchan.board.springboardjpa.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


*/
/**
 * 보통 시큐리티 인증, 인가에 대한 구현은    OncePerRequestFilter , GenericFilterBean을 상속받아 구현.
 * OncePerRequestFilter 상속받으면 더 좋은점
 * 만약 서블릿이 다른 서블릿으로 dispatch하게 되면, 다른 서블릿 앞단에서 filter chain을 한번 더 거치게 된다.
 * 이럴때 해당 filter를 거치게 되면서 동일한 보안 필터를 구성할 수 있음.
 *
 * BcryptPasswordEncoder 순환참조 오류 주의
 * 스프링 5.x 이상부터는 BcryptPasswordEncoder 의무적으로 써야함. -> 해당 Config 파일을 따로 만들어주어 해결.
 *//*

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationCustomFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("=====JWT authentication custom filter in=======");
        // URL : api 호출 && POST 형태에서만 JWT 토큰 인증 관련 Filter 수행.
        if(request.getRequestURI().contains("api") && request.getMethod().equals("POST")){
            //JWT 토큰 인증 로직
            log.info("Current URI > " + request.getRequestURI());
        }

        filterChain.doFilter(request, response);
        log.info("=====JWT authentication custom filter out=======");
    }
}
*/
