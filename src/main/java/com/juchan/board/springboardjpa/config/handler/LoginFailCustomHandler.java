package com.juchan.board.springboardjpa.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.StatusType;
import com.juchan.board.springboardjpa.api.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * <P>AuthenticationFailureHandler 대신 SimpleUrlAuthenticationFailureHandler 인터페이스 구현 받아서 ERROR MSG를 queryString 값으로 던져주는걸로 바꿈</P>
 *
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailCustomHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final String DEFAULT_FAIL_URL = "/member/login";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("=====[로그인 실패 핸들러 진입]====");
        String failMessage = "";

        // [STEP.1] 발생한 Exception에 대해서 확인한다.
        if (exception instanceof BadCredentialsException) {;
            // [STEP.2] 로그인 실패에 대한 FAIL COUNT ++ && 5회 이상 시 => STATUS 값 수정 작업.
            String loginId = request.getParameter("loginId").toString();
            //MeberService 등록 시 또 의존관계로 가지고와야함. 그냥 여기다 로직 추가하는게 더 간단할지도
            Member member = memberRepository.findByLoginId(loginId);
            log.info("=====[로그인 실패 카운팅 처리]=======");
            if(member.getFailCount() < 5){
                member.setFailCount(member.getFailCount() + 1);
                memberRepository.save(member);
            }

            int default_count = 5;
            failMessage = "[ID/PWD 확인 필요]" + "\n"
                    + " [실패 횟수] : " + member.getFailCount() + " 번" + "\n"
                    + " [남은 횟수] : " + (default_count - member.getFailCount()) + " 번";

            //실패 카운팅 5회 및 계정이 잠겨있지 않은 경우 -> 계정 잠금 처리 상태
            if(member.getFailCount() == 5){
                member.setStatus(StatusType.LOCK_PWD);
                memberRepository.save(member);
                failMessage = "[5회] 이상 입력정보 불일치로 인하여 계정 잠금처리 되었습니다." +"\n" +
                        "관리자에게 문의 부탁드립니다.";
            }
            log.info("=====[로그인 실패 카운팅 처리]=======");


        } else if (exception instanceof DisabledException) {
            failMessage = "[계정이 비활성화되었습니다.]";
        } else if (exception instanceof AccountExpiredException) {
            failMessage = "[계정이 만료되었습니다.]";
        } else if (exception instanceof CredentialsExpiredException) {
            failMessage = "[인증 정보가 만료되었습니다.]";
        }

        log.info("[로그인 실패 사유] : " + failMessage);

        failMessage = URLEncoder.encode(failMessage, "UTF-8");
        setDefaultFailureUrl(DEFAULT_FAIL_URL + "?error=true&exception=" + failMessage);
        super.onAuthenticationFailure(request, response, exception);
        log.info("=====[로그인 실패 핸들러 종료]====");
    }
}
