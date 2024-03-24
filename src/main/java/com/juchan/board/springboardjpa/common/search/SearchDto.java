package com.juchan.board.springboardjpa.common.search;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SearchDto {

    @Enumerated(EnumType.STRING)
    private String type;
    private String keyword;

    //default SearchType list
    private List<SearchType> typeList = Arrays.stream(SearchType.values()).toList();

    public SearchDto(String type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }

    public SearchDto(){
        this.type = "ALL";
    }






}
