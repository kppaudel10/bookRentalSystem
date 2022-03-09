package com.bookrent.dto.book;

import com.bookrent.entity.Author;
import com.bookrent.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "must not be empty")
    private String isbn;

    @NotEmpty(message = "must not be empty")
    private String published_date;

    @Size( message = "invalid rating value.")
    private Double rating;

    //    @Size(min = 1,message = "Can not be negative stock value")
    private Integer stockCount;

    //    @Size(min = 1, message = "Invalid book page.")
    private Integer numberOfPage;

    //    @NotEmpty(message = "category must be selected.")
    private Category category;

    //    @NotEmpty(message = "Author must be selected.")
    private List<Author> authorList;

    private MultipartFile multipartFile;

    private String coverPhotoPath;

    //    @NotEmpty(message = "book code must be selected.")
    private String bookCode;
}
