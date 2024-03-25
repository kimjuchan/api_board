package com.juchan.board.springboardjpa.common.search;


import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum SearchType {

    All("all"),
    TITLE("title"),
    CONTENT("content"),
    CREATED_BY("createdBy");

    //검색 type은 3가지 ( 타이틀 , 본문 내용, 등록자 )
    private String type;

    SearchType(String type) {
        this.type = type;
    }

    /*public static SearchType fromType(String type) {
        return Arrays.stream(SearchType.values())
                .filter(v -> v.getType().equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("검색 카테고리에 %s가 존재하지 않습니다.", type)));
    }
*/
}
