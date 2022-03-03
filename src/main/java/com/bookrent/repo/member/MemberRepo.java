package com.bookrent.repo.member;

import com.bookrent.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_member full_name set full_name = :name where id = :id", nativeQuery = true)
    void updateName(@Param("name") String name, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update brs_member email_address set email_address = :email where id = :id", nativeQuery = true)
    void updateEmail(@Param("email") String email, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update brs_member address set address = :address where id = :id", nativeQuery = true)
    void updateAddress(@Param("address") String address, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update brs_member contact_number set contact_number = :mobile where id = :id", nativeQuery = true)
    void updateMobile(@Param("mobile") String mobile, @Param("id") Integer id);

}
