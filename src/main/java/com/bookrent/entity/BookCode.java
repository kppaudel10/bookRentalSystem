package com.bookrent.entity;

import com.bookrent.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brs_book_code")
public class BookCode {
    @Id
    @GeneratedValue(generator = "bookCode_sequence" ,strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bookCode_sequence" ,sequenceName = "bookCode_sequence" ,allocationSize = 8)
    private Integer id;

    @Column(nullable = false)
    private Integer bookId;

    @Column(nullable = true ,unique = true)
    private String bookCode;

    private RentStatus rentStatus;
}
