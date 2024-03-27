package com.juchan.board.springboardjpa.config.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;


@Component
@Slf4j
public class LoginSuccessCustomHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());


        // IP, 세션 ID
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        System.out.println("IP: " + web.getRemoteAddress());
        System.out.println("Session ID: " + web.getSessionId());

        // 인증 ID
        System.out.println("ID: " + authentication.getName());

        //login success
        log.info("##LOGIN INFO##");
        log.info("##LOGIN INFO##");
        String url = request.getContextPath();
        log.info("URI > " + request.getRequestURI());
        log.info("URL > " + request.getRequestURL());
        log.info("PATH > " + request.getContextPath());

        response.sendRedirect("/article/list");
    }
}
