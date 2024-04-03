package com.juchan.board.springboardjpa.api.article.dto;


import com.juchan.board.springboardjpa.api.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "태그 값을 입력해주세요.")
    private String hashtag;

    private List<String> hashtagList;

    private Member member;

    private List<MultipartFile> files;

}
