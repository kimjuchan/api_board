package com.juchan.board.springboardjpa.config.handler;

import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;
import com.juchan.board.springboardjpa.api.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


@Component
@Slf4j
public class LoginSuccessCustomHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=====[로그인 성공 핸들러 진입]====");
        // User info
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        log.info("IP: " + web.getRemoteAddress());
        log.info("User name : " + authentication.getName());
        log.info("Session ID: " + web.getSessionId());
        log.info("Request URI > " + request.getRequestURI());
        log.info("Request URL > " + request.getRequestURL());
        log.info("Request PATH > " + request.getContextPath());
        // 로그인 성공시 이동할 페이지
        setDefaultTargetUrl("/article/list");
        // 로그인 성공시 이동할 페이지로 이동
        super.onAuthenticationSuccess(request, response, authentication);
        log.info("=====[로그인 성공 핸들러 진입]====");
    }
}
