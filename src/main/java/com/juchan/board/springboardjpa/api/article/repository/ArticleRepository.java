package com.juchan.board.springboardjpa.api.article.repository;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * QuerydslPredicateExecutor -> 통해서 검색기능 만들 시
 *  [1] 간단한 곳에서 사용이 가능
 *  [2] 조인이 불가능하다
 *
 *
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
