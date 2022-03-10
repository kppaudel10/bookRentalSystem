package com.bookrent.dto.book;

import com.bookrent.entity.Author;
import com.bookrent.entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;

    @NotEmpty(message = "Name must not be empty.")
    private String name;

    @NotEmpty(message = "Book ISBN must not be empty")
    private String isbn;

    @NotEmpty(message = "Date must not be empty")
    private String published_date;

    @NotNull(message = "Provide a book rating")
    @Min(value = 0, message = "Book rating can't be negative")
    @Max(value = 5, message = "Book rating can't be greater than 5")
    private Double rating;

    @NotNull(message = "Provide book stock count")
    @Min(value = 0, message = "Stock count can't be negative")
    private Integer stockCount;

    @NotNull(message = "Number of book pages must not be empty")
    @Min(value = 1, message = "Book must have at least 1 page")
    private Integer numberOfPage;

    private Category category;

    private List<Author> authorList;

    @NotNull(message = "Book category can't be empty")
    private Integer categoryId;

    private MultipartFile multipartFile;

    private String coverPhotoPath;

    @NotEmpty(message = "book code must be selected.")
    private String bookCode;
}
