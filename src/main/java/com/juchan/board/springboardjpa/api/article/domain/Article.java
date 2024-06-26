package com.juchan.board.springboardjpa.api.article.domain;


import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import com.juchan.board.springboardjpa.api.articleimg.doamain.ArticleImg;
import com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
//만약 해당 어노테이션 사용하게 된다면 Article 엔티티 모든 컬럼 기준으로 Equals HashCode 작업이 들어가게됨 (created, updated ... 필요없는 요소까지 포함되버림)
//-> equals : 동등성 , hashcode : 동일성 (객체 주소값까지 동일한지)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "content"),
        @Index(columnList = "createdAt")
})
public class Article extends BaseEntitiy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    //특정 필드만 setter 접근 가능 하도록 설정.
    @Column(nullable = false)
    @Setter
    private String title;

    @Column(nullable = false, length = 10000)
    @Setter
    private String content;

    @Setter
    private String hashtag;

    //orphanRemoval = true 자식 엔티티의 값이 변경되면서 발생되는 고아 객체 delete 처리
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ArticleComment> articleComments;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TagMapping> tagMappings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member; // 유저 정보 (ID)

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ArticleImg> articleImgs;

    //모든 엔티티 컬럼을 비교할 필요없시 pk 기반으로만 확인해도 충분함.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
