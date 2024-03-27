package com.juchan.board.springboardjpa.api.member.repository;

import com.juchan.board.springboardjpa.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //loginId chk
    public Member findByLoginId(String loginId);

    public boolean existsByLoginId(String loginId);
}
