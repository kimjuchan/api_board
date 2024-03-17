package com.juchan.board.springboardjpa.api.articlecomment.dto;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCommentResponse {

    //기본 컬럼 정보
    private Long id;
    private String content;

    //등록, 수정 정보
    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime updatedAt;
    private String updateBy;


    //DTO TO ENTITY TYPE CHANGE
    public static ArticleCommentResponse of(ArticleComment articleComment){
        ArticleCommentResponse response = ArticleCommentResponse.builder()
                .id(articleComment.getId())
                .content(articleComment.getContent())
                .createAt(articleComment.getCreatedAt())
                .createBy(articleComment.getCreateBy())
                .updatedAt(articleComment.getUpdatedAt())
                .updateBy(articleComment.getUpdateBy())
                .build();
        return response;
    }

}
