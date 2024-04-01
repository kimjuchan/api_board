package com.juchan.board.springboardjpa.api.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ADMIN("직원"),
    USER("일반");

    private String name;




}
