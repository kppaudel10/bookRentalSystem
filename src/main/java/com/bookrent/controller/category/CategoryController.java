package com.bookrent.controller.category;

import com.bookrent.dto.author.AuthorDto;
import com.bookrent.dto.category.CategoryDto;
import com.bookrent.entity.Category;
import com.bookrent.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String getCategoryHomePage(Model model){
        model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("data",categoryService.findAll());
        return "category/categoryPage";
    }

    @GetMapping("/addDetails")
    public String getCategoryAddPage(Model model){
        model.addAttribute("categoryDto",new CategoryDto());
        return "category/createcategory";
    }

    @PostMapping("/add")
    public String getAuthorAddPage(@Valid @ModelAttribute("categoryDto")
                                               CategoryDto categoryDto,
                                   BindingResult bindingResult,Model model){
        if (!bindingResult.hasErrors()){
            try {
                CategoryDto categoryDto1= categoryService.save(categoryDto);
                model.addAttribute("message","Category added successfully");

//            System.out.println("saved successfully");
            }catch (Exception e){
                model.addAttribute("message","Category creation failed.");
            }
        }
        model.addAttribute("categoryDto",categoryDto);
        return "category/createcategory";
    }

    @GetMapping("/update/{id}")
    public String getCategoryUpdatePage(@PathVariable Integer id,Model model){
        model.addAttribute("categoryDto",categoryService.findById(id));
        return "category/updatecategory";
    }

    @PostMapping("/update/{id}")
    public String getUpdate(@PathVariable Integer id,@ModelAttribute("categoryDto")CategoryDto categoryDto , RedirectAttributes redirectAttributes){
        try {
            CategoryDto categoryDto1 = categoryService.findById(id);
            categoryDto1.setId(id);
            categoryDto1.setTitle(categoryDto.getTitle());
            categoryDto1.setDescription(categoryDto.getDescription());
            categoryService.update(categoryDto1);
            System.out.println("save into database");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","author creation failed.");
            e.printStackTrace();
        }
        return "redirect:/category/home";
    }

    @GetMapping("/delete/{id}")
   public String getDelete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return "redirect:/category/home";
    }
}
