package com.bookrent.repo.author;

import com.bookrent.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_author name set name = :name  where  id = :id", nativeQuery = true)
    void updateName(@Param("name") String name, @Param("id") Integer id);

    @Transactional
    @Modifying()
    @Query(value = "update brs_author email set email = :email  where  id = :id"
            , nativeQuery = true)
    void updateEmail(@Param("email") String email, @Param("id") Integer id);

    @Transactional
    @Modifying()
    @Query(value = "update brs_author mobile_number " +
            "set mobile_number = :mobile  where id = :id"
            , nativeQuery = true)
    void updateMobile(@Param("mobile") String name, @Param("id") Integer id);

}
