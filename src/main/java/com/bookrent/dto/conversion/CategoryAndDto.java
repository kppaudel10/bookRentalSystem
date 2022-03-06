package com.bookrent.dto.conversion;

import com.bookrent.dto.category.CategoryDto;
import com.bookrent.entity.Category;

public class CategoryAndDto {
    public Category getCategory(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription()).build();
    }

}
