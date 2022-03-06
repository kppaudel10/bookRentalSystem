package com.bookrent.dto.book;

import com.bookrent.entity.Author;
import com.bookrent.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String name;
    private String isbn;
    private String published_date;
    private Double rating;
    private Integer stockCount;
    private Integer numberOfPage;
    private Category category;
    private List<Author> authorList;
    private MultipartFile multipartFile;
    private String coverPhotoPath;
    private String bookCode;

}
