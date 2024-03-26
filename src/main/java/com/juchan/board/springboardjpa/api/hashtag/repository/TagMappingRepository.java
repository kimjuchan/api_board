package com.juchan.board.springboardjpa.api.hashtag.repository;

import com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagMappingRepository extends JpaRepository<TagMapping,Long> {
}
