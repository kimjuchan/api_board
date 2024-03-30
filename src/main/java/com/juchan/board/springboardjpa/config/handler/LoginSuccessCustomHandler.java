package com.juchan.board.springboardjpa.config.handler;

import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;
import com.juchan.board.springboardjpa.api.member.repository.MemberRepository;
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
import java.util.Map;
import java.util.Set;


@Component
@Slf4j
public class LoginSuccessCustomHandler implements AuthenticationSuccessHandler {

    public LoginSuccessCustomHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    private MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=====[로그인 성공 핸들러 진입]====");
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        //Login fail count Reset (관리자 페이지 기능으로 옮겨야함)
        /*
        String loginId = request.getParameter("loginId");
        MemberDetail member = (MemberDetail) authentication.getPrincipal();
        Member updateMem = memberRepository.findByLoginId(member.getLoginId());
        updateMem.setFailCount(0);
        memberRepository.save(updateMem);
        */

        // User info
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        log.info("IP: " + web.getRemoteAddress());
        log.info("User name : " + authentication.getName());
        log.info("Session ID: " + web.getSessionId());
        log.info("request URI > " + request.getRequestURI());
        log.info("request URL > " + request.getRequestURL());
        log.info("request PATH > " + request.getContextPath());

        response.sendRedirect("/article/list");
        log.info("=====[로그인 성공 핸들러 진입]====");
    }
}
