package com.bookrent.dto.author;

import com.bookrent.dto.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {
    private Integer id;
    private String name;
    private String email;
    private String mobile_number;
}
