package com.juchan.board.springboardjpa.api.articlecomment.domain;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt")
})
@Builder
public class ArticleComment extends BaseEntitiy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_comment_id")
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    //만약 조인컬럼 id(외래키) 매핑 안해주면 article_article_id 새로운 외래키가 생성됨...
    @JoinColumn(name="article_id")
    private Article article;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    //만약 조인컬럼 id(외래키) 매핑 안해주면 article_article_id 새로운 외래키가 생성됨...
    @JoinColumn(name="member_id")
    private Member member;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setArticle(Article article){
        this.article = article;
        article.getArticleComments().add(this);
    }

}
