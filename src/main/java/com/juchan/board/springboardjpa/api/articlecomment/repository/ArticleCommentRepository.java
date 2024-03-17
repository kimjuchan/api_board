package com.juchan.board.springboardjpa.api.articlecomment.repository;

import com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> {
}
