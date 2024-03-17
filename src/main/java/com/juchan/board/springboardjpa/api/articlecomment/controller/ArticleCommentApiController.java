package com.juchan.board.springboardjpa.api.articlecomment.controller;

import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentResponse;
import com.juchan.board.springboardjpa.api.articlecomment.service.ArticleCommentServiceImpl;
import com.juchan.board.springboardjpa.exception.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleCommentApiController {

    private final ArticleCommentServiceImpl articleCommentServicel;

    // listAll
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ArticleCommentResponse>>> getListAll(){
        //추가적으로 상태값 , 메세지  + page 값 설정.
        List<ArticleCommentResponse> responseList = articleCommentServicel.findAll().stream().map(ArticleCommentResponse::of).toList();
        return ResponseEntity.ok(ApiResponse.create(responseList));
    }

    // detail
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleCommentResponse>> getDetail( @PathVariable Long id){
        //service에서 이미 null chk 함 -> 정상적으로 넘어온다면 값이 무조건 있으니 더 이상 예외처리 안해돔 괜찮음.
        ArticleCommentResponse response = ArticleCommentResponse.of(articleCommentServicel.findById(id));
        return ResponseEntity.ok(ApiResponse.create(response));

    }

    // create
    @PostMapping()
    public ResponseEntity<Void> createByArticle(@RequestBody @Valid ArticleCommentRequest request){
        Long createdId = articleCommentServicel.create(request);
        return ResponseEntity.created(ApiResponse.createdLocation(createdId)).build();
    }

    // upadte
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<List<ArticleCommentResponse>>> updateByArticle(@PathVariable Long id, @RequestBody @Valid ArticleUpdateRequest request){
        List<ArticleCommentResponse> articleResponseList = articleCommentServicel.update(id,request);
        return ResponseEntity.ok(ApiResponse.create(articleResponseList));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByArticle(@PathVariable Long id){
        articleCommentServicel.deleteByArticle(id);
        return ResponseEntity.noContent().build();
    }
}
