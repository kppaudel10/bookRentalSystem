package com.bookrent.dto.category;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;

    @NotEmpty(message = "title must not be empty.")
    private String title;

    @NotEmpty(message = "description must not be empty.")
    private String description;
}
