package com.juchan.board.springboardjpa.config.filter;

import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;
import com.juchan.board.springboardjpa.api.member.service.MemberDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.metamodel.model.domain.internal.MapMember;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class MemberAuthenticatorProvider implements AuthenticationProvider {
    private final MemberDetailService memberDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // 사용자가 입력한 id
        String password = (String) authentication.getCredentials(); // 사용자가 입력한 password

        // 생성해둔 MemberPrincipalDetailService 에서 loadUserByUsername 메소드를 호출하여 사용자 정보를 가져온다.
        //***여기서 만약 UserDetails 구현체인 MemberDetails로 받게 된다면 이후 Controller에서 @AuthenticationPrincipal을 통해서 인증 객체정보 받을때 null 처리됨.
        UserDetails memberPrincipalDetails =  memberDetailService.loadUserByUsername(username);

        // ====================================== 비밀번호 비교 ======================================
        // db 에 저장된 password
        String dbPassword = memberPrincipalDetails.getPassword();
        // 암호화 방식 (BCryptPasswordEncoder) 를 사용하여 비밀번호를 비교한다.
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!passwordEncoder.matches(password, dbPassword)) {
            throw new BadCredentialsException("[사용자] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        // ========================================================================================


        // 해당 객체는 Authentication 객체로 추후 인증이 끝나고 SecurityContextHolder.getContext() 에 저장된다.
        return new UsernamePasswordAuthenticationToken(memberPrincipalDetails, null, memberPrincipalDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
