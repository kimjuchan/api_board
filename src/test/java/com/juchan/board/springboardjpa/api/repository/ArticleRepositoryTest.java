package com.juchan.board.springboardjpa.api.repository;

import com.juchan.board.springboardjpa.api.article.repository.ArticleRepository;
import com.juchan.board.springboardjpa.api.articlecomment.repository.ArticleCommentRepository;
import com.juchan.board.springboardjpa.config.JPAconfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("CRUD 테스트")
@Import(JPAconfig.class)
//기본 junit5부터는 @Autowired 포함됨.
@DataJpaTest
class ArticleRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public ArticleRepositoryTest(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @DisplayName("create test")
    @Test
    public void createAritlce(){
        //given

        //when
        //List<Article> articleList = articleRepository.findAll();

        //then



    }

}