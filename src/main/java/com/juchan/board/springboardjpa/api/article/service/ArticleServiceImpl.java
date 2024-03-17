package com.juchan.board.springboardjpa.api.article.service;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.exception.NoSuchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl {
    private final ArticleRepository articleRepository;
    public Long create(ArticleRequest articleRequest){
        //set
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .hashtag(articleRequest.getHashtag())
                .build();
        //save
        return Optional.of(articleRepository.save(article).getId()).orElseThrow(() -> new RuntimeException("error type : [no create data]"));
    }

    public List<ArticleResponse> update(Long id, ArticleUpdateRequest articleUpdateRequest){

        //set
        Article article = articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [no update data]"));
        //before data
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(ArticleResponse.of(article));

        article.setTitle(articleUpdateRequest.getTitle());
        article.setContent(articleUpdateRequest.getContent());
        article.setHashtag(articleUpdateRequest.getHashtag());

        //update
        articleRepository.save(article);
        //after data
        articleResponseList.add(ArticleResponse.of(article));
        return articleResponseList;
    }

    public void deleteByArticle(Long id){
        if(articleRepository.existsById(id)){
            articleRepository.deleteById(id);
        }else{
            throw new NoSuchDataException("error type : [no delete data]");
        }
    }

    // type : entity to dto change
    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public Article findById(Long id){
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [[no data]]"));
    }

    //Article total size
    public Long findTotalCnt(){
        return articleRepository.count();
    }

}
