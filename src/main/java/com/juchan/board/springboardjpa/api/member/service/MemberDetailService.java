package com.juchan.board.springboardjpa.api.member.service;


import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;
import com.juchan.board.springboardjpa.api.member.domain.RoleType;
import com.juchan.board.springboardjpa.api.member.domain.StatusType;
import com.juchan.board.springboardjpa.api.member.dto.MemberRequest;
import com.juchan.board.springboardjpa.api.member.repository.MemberRepository;
import com.juchan.board.springboardjpa.api.test.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //fail count chk
    public int chkFailCount(String logindId){
        //로그인 유저의 로그인 실패 카운트 값 가져옴
        return Optional.of(memberRepository.findByLoginId(logindId).getFailCount()).orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보 없음"));
    }

    //TODO : 추후 잠긴 계정은 패스워드 찾기 && 변경 로직에서 풀어줘야함 (상태 값 : 4 , failcount : 0)
    public void changeMemberByPWD(){

    }

    public Long createUser(MemberRequest memberRequest){

        //pwd encode
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));
        Member member = memberRequest.of();
        member.setRoleType(RoleType.USER);
        //exception 처리
        Long userId = Optional.of(memberRepository.save(member).getId()).orElseThrow(() -> new RuntimeException("User Create 도중 에러 발생"));
        return userId;
    }

    public void dupChkByLoginId(MemberRequest memberRequest){
        boolean dupChk = memberRepository.existsByLoginId(memberRequest.getLoginId());
        if(!dupChk){
            createUser(memberRequest);
        }else{
            throw new RuntimeException("이미 가입된 userId 입니다.");
        }
    }

    public boolean chkPassword(String password){
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member mem = memberRepository.findByLoginId(loginId);
        Member member = Optional.of(memberRepository.findByLoginId(loginId)).orElseThrow(() -> new UsernameNotFoundException("매칭되는 유저 ID 없음"));
        return new MemberDetail(member);
    }

}
