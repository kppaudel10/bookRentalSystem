package com.bookrent.service.impl;

import com.bookrent.dto.category.CategoryDto;
import com.bookrent.entity.Category;
import com.bookrent.repo.category.CategoryRepo;
import com.bookrent.service.category.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    //get repo for work with data
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        //save into database
        //first make category entity
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        //use repo to save object
       Category category1 = categoryRepo.save(category);
        System.out.println("successfully saved..");
        return null;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepo.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(category -> {
            return CategoryDto.builder()
                    .id(category.getId())
                    .title(category.getTitle())
                    .description(category.getDescription()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer integer) {
       Optional<Category> category= categoryRepo.findById(integer);
       Category category1;
       if (category.isPresent()){
           category1 = category.get();
          return CategoryDto.builder()
                   .id(category1.getId())
                   .title(category1.getTitle())
                   .description(category1.getDescription()).build();
       }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepo.deleteById(integer);

    }

    @Override
    public void update(CategoryDto categoryDto) {
        Integer id = categoryDto.getId();
        categoryRepo.updateTitle(categoryDto.getTitle(),id);
        categoryRepo.updateDescription(categoryDto.getDescription(),id);

//        Category category = new Category(categoryDto.getId(),categoryDto.getTitle(),categoryDto.getDescription());
//        categoryRepo.save(category);
    }


}
