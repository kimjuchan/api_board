package com.juchan.board.springboardjpa.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.StatusType;
import com.juchan.board.springboardjpa.api.member.dto.MemberRequest;
import com.juchan.board.springboardjpa.api.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailCustomHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final String DEFAULT_FAIL_URL = "/member/login";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("=====Login is Failed====");
        String failMessage = "";

        // [STEP.1] 발생한 Exception에 대해서 확인한다.
        if (exception instanceof BadCredentialsException) {;
            // [STEP.2] 로그인 실패에 대한 FAIL COUNT ++ && 5회 이상 시 => STATUS 값 수정 작업.
            String loginId = request.getParameter("loginId").toString();
            //MeberService 등록 시 또 의존관계로 가지고와야함. 그냥 여기다 로직 추가하는게 더 간단할지도
            Member member = memberRepository.findByLoginId(loginId);
            log.info("=====failCount ++ =======");
            if(member.getFailCount() < 5){
                member.setFailCount(member.getFailCount() + 1);
                memberRepository.save(member);
            }
            //실패 카운팅 5회 및 계정이 잠겨있지 않은 경우 -> 계정 잠금 처리 상태
            if(member.getFailCount() == 5 && !member.getStatus().toString().equals("5")){
                member.setStatus(StatusType.LOCK_PWD);
                memberRepository.save(member);
            }
            log.info("=====failCount ++ =======");
            int default_count = 5;
            failMessage = "로그인 정보 불일치 > 실패 횟수 : [" + member.getFailCount() + "]" + (default_count - member.getFailCount()) + " 번 남으셨습니다.";

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
        request.setAttribute("errorMessage", failMessage);
        String test =request.getAttribute("errorMessage").toString();
        request.getRequestDispatcher(DEFAULT_FAIL_URL).forward(request,response);
        log.info("=====Login is Failed====");
    }
}
