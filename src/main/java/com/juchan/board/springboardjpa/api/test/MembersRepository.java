package com.juchan.board.springboardjpa.api.test;

import com.juchan.board.springboardjpa.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members,Long> {

}
