package com.juchan.board.springboardjpa.common;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import com.juchan.board.springboardjpa.api.articlecomment.dto.ArticleCommentRequest;
import com.juchan.board.springboardjpa.api.articlecomment.repository.ArticleCommentRepository;
import com.juchan.board.springboardjpa.exception.NoSuchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateCommonServiceImp {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final ResourceLoader resourceLoader;

    //file path
    @Value("${json.path}")
    private String jsonFilePath;
    //확장자
    private String ext = ".json";

    //파라미터 형태를 제네릭으로 선언하면  LinkedHashMap cast exception 발생하는데 해당 부분이  spring boot 3.x에서 발생하는 버그라는데...
/*
    public <T> List<T> readJsonFile(String fileName, Class<T> type) throws IOException{
        // ResourceLoader를 사용하여 JSON 파일을 로드
        Resource data = resourceLoader.getResource("classpath:" + fileName);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(data.getInputStream(), new TypeReference<List<T>>(){});
    }

    //article save
    public void creatArticle(String fileNm) throws IOException {

        List<ArticleRequest> articleRequest  = readJsonFile(jsonFilePath + fileNm + ext, ArticleRequest.class);

        List<Article> articleList = articleRequest.stream()
                .map(data ->
                        Article.builder()
                                .title(data.getTitle())
                                .content(data.getContent())
                                .hashtag(data.getHashtag())
                                .build()
                )
                .collect(Collectors.toList());
        articleRepository.saveAll(articleList);
    }

    //article comment save
    public void creatArticleComment(String fileNm) throws IOException {
        List<ArticleCommentRequest> articleCommentRequests  = readJsonFile(jsonFilePath + fileNm + ext, ArticleCommentRequest.class);

        List<ArticleComment> articleList = articleCommentRequests.stream()
                .map(data ->
                        ArticleComment.builder()
                                .content(data.getContent())
                                .build()
                )
                .collect(Collectors.toList());
        articleCommentRepository.saveAll(articleList);
    }*/

    public List<ArticleRequest> readJsonFile_Article(String fileName) throws IOException{
        // ResourceLoader를 사용하여 JSON 파일을 로드
        Resource data = resourceLoader.getResource("classpath:" + fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data.getInputStream(), new TypeReference<List<ArticleRequest>>(){});
    }

    public List<ArticleCommentRequest> readJsonFile_ArticleComment(String fileName) throws IOException{
        // ResourceLoader를 사용하여 JSON 파일을 로드
        Resource data = resourceLoader.getResource("classpath:" + fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data.getInputStream(), new TypeReference<List<ArticleCommentRequest>>(){});
    }

    //article save
    public void creatArticle(String fileNm) throws IOException {

        List<ArticleRequest> articleRequest  = readJsonFile_Article(jsonFilePath + fileNm + ext);

        List<Article> articleList = articleRequest.stream()
                .map(data ->
                        Article.builder()
                                .title(data.getTitle())
                                .content(data.getContent())
                                .hashtag(data.getHashtag())
                                .build()
                )
                .collect(Collectors.toList());
        articleRepository.saveAll(articleList);
    }

    //article comment save
    public void creatArticleComment(String fileNm) throws IOException {
        List<ArticleCommentRequest> articleCommentRequests  = readJsonFile_ArticleComment(jsonFilePath + fileNm + ext);

        List<ArticleComment> articleList = articleCommentRequests.stream()
                .map(data ->
                        ArticleComment.builder()
                                .content(data.getContent())
                                .article(articleRepository.findById(data.getArticle_id()).orElseThrow(() -> new NoSuchDataException("no data")))
                                .build()
                )
                .collect(Collectors.toList());
        articleCommentRepository.saveAll(articleList);
    }


}
