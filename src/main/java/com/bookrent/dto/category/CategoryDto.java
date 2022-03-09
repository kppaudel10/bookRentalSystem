package com.bookrent.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
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
