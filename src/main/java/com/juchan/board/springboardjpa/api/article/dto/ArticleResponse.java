package com.juchan.board.springboardjpa.api.article.dto;


import com.juchan.board.springboardjpa.api.article.domain.Article;
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
public class ArticleResponse {

    //기본 컬럼 정보
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    //댓글 정보
    //private List<ArticleComment> articleComment = new ArrayList<>();

    //등록, 수정 정보
    private LocalDateTime createAt;
    private String createBy;
    private LocalDateTime updatedAt;
    private String updateBy;

    //DTO TO ENTITY TYPE CHANGE
    public static ArticleResponse of(Article article){
        ArticleResponse articleResponse = ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .hashtag(article.getHashtag())
                //.articleComment(article.getArticleComments())
                .createAt(article.getCreatedAt())
                .createBy(article.getCreateBy())
                .updatedAt(article.getUpdatedAt())
                .updateBy(article.getUpdateBy())
                .build();
        return articleResponse;
    }
}
