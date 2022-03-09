package com.bookrent.repo.transaction;

import com.bookrent.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_transaction rent_status set rent_status = 1 where id = :id", nativeQuery = true)
    void updateRentStatus(@Param("id") Integer id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update brs_transaction return_date set return_date = :date where id = :id", nativeQuery = true)
    void updateReturnDate(@Param("date") Date date, @Param("id") Integer id);
}
