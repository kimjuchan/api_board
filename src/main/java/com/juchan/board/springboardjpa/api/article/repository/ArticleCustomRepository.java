package com.juchan.board.springboardjpa.api.article.repository;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.common.search.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleCustomRepository {

    Page<Article> search(SearchDto searchDto, Pageable pageable);

}
