package com.juchan.board.springboardjpa.api.article.dto;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ArticleDetailView {

    private Long id;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createBy;
    private LocalDateTime updatedAt;
    private String updateBy;

    private List<ArticleComment> articleComment;

    private Member member;

    //change Article to ArticleDetailView
    public static ArticleDetailView entityToArticleVeiw(Article article){
        return ArticleDetailView.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .hashtag(article.getHashtag())
                .createdAt(article.getCreatedAt())
                .createBy(article.getCreateBy())
                .updatedAt(article.getUpdatedAt())
                .updateBy(article.getUpdateBy())
                .articleComment(article.getArticleComments())
                .member(article.getMember())
                .build();
    }
}
