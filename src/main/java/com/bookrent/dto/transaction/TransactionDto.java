package com.bookrent.dto.transaction;

import com.bookrent.entity.Book;
import com.bookrent.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Integer id;

    private String bookCode;

    private Book book;

    private Member member;

    private Integer noOfDays;

}
