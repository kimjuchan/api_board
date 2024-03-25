package com.juchan.board.springboardjpa.api.articlecomment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCommentUpdateRequest {

    @NotBlank(message = "수정할 댓글 내용을 입력해주세요.")
    private String content;
}
