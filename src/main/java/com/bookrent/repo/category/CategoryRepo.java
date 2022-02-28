package com.bookrent.repo.category;

import com.bookrent.entity.Category;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Transactional
    @Modifying()
    @Query(value = "update brs_category title set title = :title where id = :id", nativeQuery = true)
    void updateTitle(@Param("title") String title , @Param("id") Integer id);

    @Transactional
    @Modifying()
    @Query(value = "update brs_category description set  description= :description where id = :id", nativeQuery = true)
    void updateDescription(@Param("description") String description , @Param("id") Integer id);
}
