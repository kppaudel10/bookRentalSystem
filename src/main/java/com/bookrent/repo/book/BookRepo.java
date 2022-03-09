package com.bookrent.repo.book;

import com.bookrent.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book stock_count set stock_count = :stockValue WHERE id = :book_id",nativeQuery = true)
    void updateBookStock(@Param("book_id") Integer book_id, @Param("stockValue") Integer stockValue);
}
