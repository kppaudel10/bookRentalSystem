package com.bookrent.repo.bookCode;

import com.bookrent.entity.BookCode;
import com.bookrent.enums.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookCodeRepo extends JpaRepository<BookCode, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from brs_book_code where book_id = :bookId", nativeQuery = true)
    void deleteAllBookCode(@Param("bookId") Integer bookId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_book_code rent_status set rent_status = :rentStatus where id = :id", nativeQuery = true)
    void updateRentStatus(@Param("id") Integer id, @Param("rentStatus") Integer rentStatus);


}
