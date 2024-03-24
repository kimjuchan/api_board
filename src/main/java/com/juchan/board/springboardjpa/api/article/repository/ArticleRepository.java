package com.juchan.board.springboardjpa.api.article.repository;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleCustomRepository{

}
