package com.juchan.board.springboardjpa.api.articlecomment.dto;

import com.juchan.board.springboardjpa.api.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCommentRequest {

    private Long article_id;
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;

    private Member member;
}
