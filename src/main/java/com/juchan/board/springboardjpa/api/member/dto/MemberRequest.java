package com.juchan.board.springboardjpa.api.member.dto;


import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.RoleType;
import com.juchan.board.springboardjpa.api.member.domain.StatusType;
import com.juchan.board.springboardjpa.common.page.PageUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberRequest {

    //TODO : 추가로 프로필 이미지 파일까지 받아줘야함.
    @NotBlank(message = "loginId 정보없음")
    private String loginId;

    @NotBlank(message = "pwd 정보없음")
    @Size(min=4, max = 20, message = "패스워드는 최소 4자리 이상, 20자리 이하로 입력 해주세요")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 a - z , 숫자 0 -9만 입력 가능")
    private String password;

    @NotBlank(message = "email 정보없음")
    @Email(message = "이메일 형식에 맞게 작성 부탁드립니다")
    private String email;

    private String name;
    private String phone;

    private int failCount;
    private String status;

    // request to entity type change
    public Member of(){
        return Member.builder()
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .status(StatusType.findByStatus(this.status))
                //default로 적용.
                .roleType(RoleType.ROLE_USER)
                .failCount(this.failCount)
                .build();
    }

}
