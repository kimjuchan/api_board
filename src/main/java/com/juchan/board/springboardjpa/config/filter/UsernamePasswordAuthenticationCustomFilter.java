package com.juchan.board.springboardjpa.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juchan.board.springboardjpa.api.member.dto.MemberRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationCustomFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //1. body 에서 로그인 정보 받아오기
        MemberRequest memberRequest = null;

        try {
            memberRequest = objectMapper.readValue(request.getInputStream(), MemberRequest.class);
        } catch (IOException e) {
            throw new RuntimeException("Internal server error :  Login Dto Setting error" );
        }

        //2. Login Id, Password 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(memberRequest.getLoginId() , memberRequest.getPassword());

        //3. User Password 인증이 이루어지는 부분
        // 상위 클래스 타고타고 ..Provider 거쳐서 쭈욱 가다보면 내부 메소드 retrieveUser()에서 loadUserByUserName 호출함.
        //"authenticate" 가 실행될때 "PrincipalDetailService.loadUserByUsername" 실행
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return authenticate;
    }
}
