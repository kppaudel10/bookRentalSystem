package com.bookrent.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String name;
    private String isbn;
    private Date published_date;
    private Double rating;
    private Integer stockCount;
    private String filePath;
}
