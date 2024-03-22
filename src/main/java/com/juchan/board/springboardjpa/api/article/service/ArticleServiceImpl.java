package com.juchan.board.springboardjpa.api.article.service;



import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.exception.NoSuchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    // api
    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    // view
    public Page<Article> findAll(Pageable pageInfo){
        //default sort 기준 : "id" 사실 여기서 정렬을 할 필요가 있을지는 모르겠지만 나중에 추가로 사용하게 된다면 이렇게 사용한다는정도만... 샘플로
        //Pageable pageable = pageInfo.toPageableWithSort(Sort.by(Sort.Direction.ASC, pageInfo.getSort_type()));
        return articleRepository.findAll(pageInfo);
    }

    public Article findById(Long id){
        //TODO : Server에서 예외처리 후 해당 error 정보를 화면에 노출 시켜야함
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [[no data]]"));
    }

    //Article total size
    public Long findTotalCnt(){
        return articleRepository.count();
    }

}
