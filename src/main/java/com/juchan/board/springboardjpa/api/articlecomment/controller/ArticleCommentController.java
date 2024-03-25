package com.juchan.board.springboardjpa.api.articlecomment.controller;

import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentResponse;
import com.juchan.board.springboardjpa.api.articlecomment.service.ArticleCommentServiceImpl;
import com.juchan.board.springboardjpa.exception.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article/comment")
public class ArticleCommentController {

    private final ArticleCommentServiceImpl articleCommentServicel;

    // update
    @PutMapping("/update/{id}/comment/{cid}")
    public void updateByComment(@PathVariable Long id, @PathVariable Long cid,@ModelAttribute @Valid ArticleUpdateRequest request){
        articleCommentServicel.updateByComment(cid,request);
    }

    // delete
    @DeleteMapping("/delete/{id}/comment/{cid}")
    public String getDetail( @PathVariable Long id, @PathVariable Long cid){
        //id : Article Id , cid : ArticleComment Id
        articleCommentServicel.deleteByArticle(cid);
        return "redirect:/article/" + id;
    }

    // create
    @PostMapping("/create/{id}")
    public String createByArticle(@PathVariable Long id, @ModelAttribute @Valid ArticleCommentRequest request){
        //TODO : 댓글 목록은 비동기 통신으로 나중에 변경할 예정
        articleCommentServicel.createByArticle(id,request);
        return "redirect:/article/detail/"+id;
    }

}
