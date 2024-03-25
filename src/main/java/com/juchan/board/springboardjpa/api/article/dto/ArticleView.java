package com.juchan.board.springboardjpa.api.article.dto;


import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


//화면으로 제공 되는 article vo 역할
@Getter
@Setter
@Builder
public class ArticleView {

    private Long id;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createBy;
    private LocalDateTime updatedAt;
    private String updateBy;

    private List<ArticleComment> articleComment;

    //change Article to ArticleView
    public static ArticleView entityToArticleVeiw(Article article){
        return ArticleView.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .hashtag(article.getHashtag())
                    .createdAt(article.getCreatedAt())
                    .createBy(article.getCreateBy())
                    .updatedAt(article.getUpdatedAt())
                    .updateBy(article.getUpdateBy())
                    .articleComment(article.getArticleComments())
                    .build();
    }

}
