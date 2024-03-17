package com.juchan.board.springboardjpa.api.article.controller;


import com.juchan.board.springboardjpa.api.aop.CreateDataList;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.exception.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleApiController {

    //Artice Rest Api Controller
    private final ArticleServiceImpl articleService;

    // listAll
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ArticleResponse>>> getListAll(){
        //추가적으로 상태값 , 메세지  + page 값 설정.
        List<ArticleResponse> responseList = articleService.findAll().stream().map(ArticleResponse::of).toList();
        return ResponseEntity.ok(ApiResponse.create(responseList));
    }

    // detail
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArticleResponse>> getDetail( @PathVariable Long id){
        //service에서 이미 null chk 함 -> 정상적으로 넘어온다면 값이 무조건 있으니 더 이상 예외처리 안해돔 괜찮음.
        ArticleResponse response = ArticleResponse.of(articleService.findById(id));
        return ResponseEntity.ok(ApiResponse.create(response));

    }

    // create
    @PostMapping()
    public ResponseEntity<Void> createByArticle(@RequestBody @Valid ArticleRequest articleRequest){
        Long createdId = articleService.create(articleRequest);
        return ResponseEntity.created(ApiResponse.createdLocation(createdId)).build();
    }

    // upadte
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<List<ArticleResponse>>> updateByArticle(@PathVariable Long id, @RequestBody @Valid ArticleUpdateRequest articleUpdateRequest){
        List<ArticleResponse> articleResponseList = articleService.update(id,articleUpdateRequest);
        return ResponseEntity.ok(ApiResponse.create(articleResponseList));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByArticle(@PathVariable Long id){
        articleService.deleteByArticle(id);
        return ResponseEntity.noContent().build();
    }

}
