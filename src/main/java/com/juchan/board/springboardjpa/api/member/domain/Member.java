package com.juchan.board.springboardjpa.api.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juchan.board.springboardjpa.common.BaseEntitiy;
import jakarta.persistence.*;
import lombok.*;

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

    //private String imgUrl;

    @JsonIgnore
    @Setter
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;



}
