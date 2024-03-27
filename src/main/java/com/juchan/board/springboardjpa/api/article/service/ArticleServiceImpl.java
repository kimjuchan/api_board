package com.juchan.board.springboardjpa.api.article.service;



import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleResponse;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import com.juchan.board.springboardjpa.api.hashtag.domain.HashTag;
import com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping;
import com.juchan.board.springboardjpa.api.hashtag.repository.HashTagRepository;
import com.juchan.board.springboardjpa.api.hashtag.repository.TagMappingRepository;
import com.juchan.board.springboardjpa.common.search.SearchDto;
import com.juchan.board.springboardjpa.exception.NoSuchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ArticleServiceImpl {

    private final ArticleRepository articleRepository;
    private final HashTagRepository hashTagRepository;
    private final TagMappingRepository tagMappingRepository;

    public Long create(ArticleRequest articleRequest){
        // 저장 순서
        // 1) article 저장
        // 2) hashTag 저장
        // 3) article 정보 hashtag 정보 -> tagMapping 저장.
        //set
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .content(articleRequest.getContent())
                .hashtag(articleRequest.getHashtag())
                .build();
        //save
        Long article_id = Optional.of(articleRepository.save(article).getId()).orElseThrow(() -> new RuntimeException("error type : [no create data]"));

        for(String tag : articleRequest.getHashtagList()){
            HashTag hashtag = HashTag.builder()
                    .content(tag)
                    .build();
            hashTagRepository.save(hashtag);
            //TagMapping save
            TagMapping tagMapping = TagMapping.builder()
                    .hashTag(hashtag)
                    .article(article)
                    .build();
            tagMappingRepository.save(tagMapping);
        }
        return article_id;
    }

    // update
    public List<ArticleResponse> update(Long id, ArticleUpdateRequest articleUpdateRequest){

        Article article = articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [no update data]"));

        //before data (test용) start
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        articleResponseList.add(ArticleResponse.of(article));
        //before data (test용) end

        article.setTitle(articleUpdateRequest.getTitle());
        article.setContent(articleUpdateRequest.getContent());
        article.setHashtag(articleUpdateRequest.getHashtag());

        //set
        //update
        articleRepository.save(article);
        //after data
        articleResponseList.add(ArticleResponse.of(article));
        return articleResponseList;
    }

    // delete
    public void deleteByArticle(Long id){
        if(articleRepository.existsById(id)){
            articleRepository.deleteById(id);
        }else{
            throw new NoSuchDataException("error type : [no delete data]");
        }
    }

    // type : entity to dto change
    // api
    @Transactional(readOnly = true)
    public List<Article> findAll(){
        /*log.info("=======1차 findAll=========");
        Optional<Article> articles = articleRepository.findById(95L);
        log.info("=======2차 findAll -> 1차 캐시에서 조회=========");
        Optional<Article> articles2 = articleRepository.findById(95L);
        List<ArticleComment> articleComments = articles2.isPresent() ? articles2.get().getArticleComments() : null;
        */
        return articleRepository.findAll();
    }


    //findAllBySearch
    @Transactional(readOnly = true)
    public Page<Article> findAllBySearch(SearchDto searchDto,Pageable pageable){
        return articleRepository.search(searchDto,pageable);
    }

    // -----------<p>view 화면에 쓰이는 메소드 </p>--------------------
    @Transactional(readOnly = true)
    public Page<Article> findAll(Pageable pageInfo){
        //default sort 기준 : "id" 사실 여기서 정렬을 할 필요가 있을지는 모르겠지만 나중에 추가로 사용하게 된다면 이렇게 사용한다는정도만... 샘플로
        //Pageable pageable = pageInfo.toPageableWithSort(Sort.by(Sort.Direction.ASC, pageInfo.getSort_type()));
        return articleRepository.findAll(pageInfo);
    }

    // detail
    @Transactional(readOnly = true)
    public Article findById(Long id){
        //TODO : Server에서 예외처리 후 해당 error 정보를 화면에 노출 시켜야함
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchDataException("error type : [[no data]]"));
    }


    //update
    public Long updateByArticle(ArticleUpdateRequest articleUpdateRequest){

        //set
        Article article = articleRepository.findById(articleUpdateRequest.getCommentId()).orElseThrow(() -> new NoSuchDataException("error type : [no update data]"));

        article.setTitle(articleUpdateRequest.getTitle());
        article.setContent(articleUpdateRequest.getContent());
        article.setHashtag(articleUpdateRequest.getHashtag());

        //update
        return articleRepository.save(article).getId();
    }
}
