package com.juchan.board.springboardjpa.api.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @ColumnDefault("0")
    @Setter
    private int failCount;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private StatusType status;

    //private String imgUrl;

    @JsonIgnore
    @Setter
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /*
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articleList;*/
}
