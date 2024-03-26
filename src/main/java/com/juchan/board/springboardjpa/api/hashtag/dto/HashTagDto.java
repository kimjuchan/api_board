package com.juchan.board.springboardjpa.api.hashtag.dto;


import com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping;
import com.juchan.board.springboardjpa.api.hashtag.domain.HashTag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class HashTagDto {

    private Long id;
    private String content;

    private List<TagMapping> tagMapping;

    //change Article to ArticleView
    public static HashTagDto of(HashTag hashTag){
        return HashTagDto.builder()
                .id(hashTag.getId())
                .content(hashTag.getContent())
                .tagMapping(hashTag.getTagMapping())
                .build();
    }

}
