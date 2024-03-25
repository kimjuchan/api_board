package com.juchan.board.springboardjpa.api.articlecomment.service;


import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentRequest;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentResponse;
import com.juchan.board.springboardjpa.api.articlecomment.repository.ArticleCommentRepository;
import com.juchan.board.springboardjpa.exception.NoSuchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleCommentServiceImpl {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    public Long create(ArticleCommentRequest request){
        //set
        ArticleComment articleComment = ArticleComment.builder()
                .id(request.getArticle_id())
                .content(request.getContent())
                .build();
        //save
        return Optional.of(articleCommentRepository.save(articleComment).getId()).orElseThrow(() -> new RuntimeException("error type : [no create data]"));
    }

    public List<ArticleCommentResponse> update(Long id, ArticleUpdateRequest request){

        //set
        ArticleComment articleComment = articleCommentRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [no update data]"));
        //before data
        List<ArticleCommentResponse> responseList = new ArrayList<>();
        responseList.add(ArticleCommentResponse.of(articleComment));

        articleComment.setContent(request.getContent());

        //update
        articleCommentRepository.save(articleComment);
        //after data
        responseList.add(ArticleCommentResponse.of(articleComment));
        return responseList;
    }

    public void deleteByArticle(Long id){
        if(articleCommentRepository.existsById(id)){
            articleCommentRepository.deleteById(id);
        }else{
            throw new NoSuchDataException("error type : [no delete data]");
        }
    }

    // type : entity to dto change
    public List<ArticleComment> findAll(){
        return articleCommentRepository.findAll();
    }

    public ArticleComment findById(Long id){
        return articleCommentRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [[no data]]"));
    }


    //Article SSR 구성에서 사용되는 Method...
    public void createByArticle(Long id,ArticleCommentRequest request){
        //Article info set
        Article article = articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("[" + id + "]에 매핑되는 정보가 없습니다."));

        //set
        ArticleComment articleComment = ArticleComment.builder()
                .id(request.getArticle_id())
                .content(request.getContent())
                .article(article)
                .build();

        //save
        articleCommentRepository.save(articleComment);
    }

    public void updateByComment(Long cid, ArticleUpdateRequest request){

        //set
        ArticleComment articleComment = articleCommentRepository.findById(cid).orElseThrow(() -> new NoSuchDataException("error type : [no update data]"));
        articleComment.setContent(request.getContent());

        //update
        articleCommentRepository.save(articleComment);
    }


}
