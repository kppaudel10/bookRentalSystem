package com.bookrent.repo.member;

import com.bookrent.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Integer> {

}
