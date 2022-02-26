package com.bookrent.repo;

import com.bookrent.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Integer> {

}
