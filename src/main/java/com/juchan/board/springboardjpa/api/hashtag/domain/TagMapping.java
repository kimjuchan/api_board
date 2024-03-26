package com.juchan.board.springboardjpa.api.hashtag.domain;


import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagMapping extends BaseEntitiy {

    //새로운 index key 생성해서 연관관계의 새로운 FK 지정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_id")
    private HashTag hashTag;

}
