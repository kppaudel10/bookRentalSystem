package com.bookrent.repo.book;

import com.bookrent.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book stock_count set stock_count = :stockValue WHERE id = :book_id",nativeQuery = true)
    void updateBookStock(@Param("book_id") Integer book_id, @Param("stockValue") Integer stockValue);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book name set name = :name WHERE id = :book_id",nativeQuery = true)
    void updateBookName(@Param("book_id") Integer book_id, @Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book Isbn set Isbn = :Isbn WHERE id = :book_id",nativeQuery = true)
    void updateBookISbn(@Param("book_id") Integer book_id, @Param("Isbn") String Isbn);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book published_date set published_date = :published_date WHERE id = :book_id",nativeQuery = true)
    void updateBookPublishedDate(@Param("book_id") Integer book_id, @Param("published_date")Date published_date);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book file_path set file_path = :file_path WHERE id = :book_id",nativeQuery = true)
    void updateBookCoverPath(@Param("book_id") Integer book_id, @Param("file_path") String file_path);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book rating set rating = :rating WHERE id = :book_id",nativeQuery = true)
    void updateBookRating(@Param("book_id") Integer book_id, @Param("rating") Double rating);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book number_of_page set number_of_page = :number_of_page WHERE id = :book_id",nativeQuery = true)
    void updateBookPage(@Param("book_id") Integer book_id, @Param("number_of_page") Integer number_of_page);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book category_id set category_id  = :category_id  WHERE id = :book_id",nativeQuery = true)
    void updateBookCategory(@Param("book_id") Integer book_id, @Param("category_id ") Integer category_id );

}
