package com.juchan.board.springboardjpa.api.articlecomment.controller;

import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentResponse;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentUpdateRequest;
import com.juchan.board.springboardjpa.api.articlecomment.service.ArticleCommentServiceImpl;
import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;
import com.juchan.board.springboardjpa.exception.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article/comment")
public class ArticleCommentController {

    private final ArticleCommentServiceImpl articleCommentServicel;

    // update
    @GetMapping("/update/{id}/comment/{cid}")
    public String updateByComment(@PathVariable Long id, @PathVariable Long cid, @Valid ArticleCommentUpdateRequest request){
        //id : Article Id , cid : ArticleComment Id
        articleCommentServicel.updateByComment(cid,request);
        return "redirect:/article/detail/" + id;
    }

    // delete
    @GetMapping("/delete/{id}/comment/{cid}")
    public String getDetail( @PathVariable Long id, @PathVariable Long cid){
        //id : Article Id , cid : ArticleComment Id
        articleCommentServicel.deleteByArticle(cid);
        return "redirect:/article/detail/" + id;
    }

    // create
    @PostMapping("/create/{id}")
    public String createByArticle(@AuthenticationPrincipal MemberDetail memberdetail,
                                  @PathVariable Long id,
                                  @ModelAttribute @Valid ArticleCommentRequest request){
        //TODO : 댓글 목록은 비동기 통신으로 나중에 변경할 예정
        //Login 사용자 정보 설정
        request.setMember(memberdetail.member);
        articleCommentServicel.createByArticle(id,request);
        return "redirect:/article/detail/"+id;
    }

}
