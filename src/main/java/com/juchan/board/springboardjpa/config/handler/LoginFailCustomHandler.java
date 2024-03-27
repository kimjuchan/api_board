package com.juchan.board.springboardjpa.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class LoginFailCustomHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("=====Login is Failed====");
        String failMessage = "";

        // [STEP.2] 발생한 Exception에 대해서 확인한다.
        if (exception instanceof BadCredentialsException) {
            failMessage = "로그인 정보가 일치하지 않습니다.";
            //Login Fail Count ++
            //5회 이상 실패 시 계정 "L"  잠김 상태 처리
        } else if (exception instanceof LockedException) {
            failMessage = "계정이 잠겨 있습니다.";
        } else if (exception instanceof DisabledException) {
            failMessage = "계정이 비활성화되었습니다.";
        } else if (exception instanceof AccountExpiredException) {
            failMessage = "계정이 만료되었습니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            failMessage = "인증 정보가 만료되었습니다.";
        }

        log.info("result fail Msg >> " + failMessage);
        //fail cnt ++
        //이후 혹시나 가지고 잇는 session도 삭제처리
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", failMessage);
        response.sendRedirect("/member/login");
        log.info("=====Login is Failed====");
    }
}
