package com.juchan.board.springboardjpa.api.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {

    SLEEP("0"),
    NORMAL("4"),
    LOCK_PWD("5"),
    DELETE("9");

    private final String status;
}
