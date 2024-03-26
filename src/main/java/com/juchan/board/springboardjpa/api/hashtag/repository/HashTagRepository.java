package com.juchan.board.springboardjpa.api.hashtag.repository;
import com.juchan.board.springboardjpa.api.hashtag.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag,Long> {
}
