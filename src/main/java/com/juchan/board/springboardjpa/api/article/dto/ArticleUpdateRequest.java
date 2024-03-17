package com.juchan.board.springboardjpa.api.article.dto;


import com.juchan.board.springboardjpa.api.article.domain.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleUpdateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "태그 값을 입력해주세요.")
    private String hashtag;

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .hashtag(this.hashtag)
                .build();
    }
}
