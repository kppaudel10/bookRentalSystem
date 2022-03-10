package com.bookrent.dto.transaction;

import com.bookrent.enums.RentStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
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
