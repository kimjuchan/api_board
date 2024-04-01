package com.juchan.board.springboardjpa.api.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ROLE_ADMIN("직원"),
    ROLE_USER("일반");

    private String name;




}
