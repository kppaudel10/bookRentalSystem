package com.bookrent.dto.transaction;

import com.bookrent.entity.Book;
import com.bookrent.entity.Member;
import com.bookrent.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Integer id;

    private String bookCode;

    private RentStatus rentStatus;

    private Integer bookId;

    private Integer memberId;

    private Integer noOfDays;

    private Date fromDate;

    private Date returnDate;

}
