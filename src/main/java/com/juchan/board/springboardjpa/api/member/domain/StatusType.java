package com.juchan.board.springboardjpa.api.member.domain;

import com.juchan.board.springboardjpa.exception.NoMathedEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


//기본적으로 직렬화 가능
@Getter
public enum StatusType {

    SLEEP("0"),
    NORMAL("4"),
    LOCK_PWD("5"),
    DELETE("9");

    private final String status;


    //default constructor
    StatusType(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }

    public boolean hasStatus(String status){
        return getStatus().equals(status);
    }

    public static StatusType findByStatus(String status) {
        return Arrays.stream(StatusType.values())
                .filter(st -> st.hasStatus(status))
                .findAny()
                .orElseThrow(() -> new NoMathedEnumCode("[" + status + "] 와 일치되는 enum code 값이 없습니다."));
    }
}
