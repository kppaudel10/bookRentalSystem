package com.bookrent.controller.book;

import com.bookrent.dto.book.BookDto;
import com.bookrent.dto.category.CategoryDto;
import com.bookrent.service.book.BookServiceImpl;
import com.bookrent.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;
    private final CategoryServiceImpl categoryService;
    public BookController(BookServiceImpl bookService, CategoryServiceImpl categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String getBookHomePage(Model model){
        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("data",bookService.findAll());
        return "book/bookPage";
    }

    @GetMapping("/add")
    public String getBookAddPage(Model model){
        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("categoryList" ,categoryService.findAll());
//        List<CategoryDto> categoryDtoList = categoryService.findAll();

//        List<String> categoryName = new ArrayList<>();
//        for (Integer i = 0 ; i<categoryDtoList.size();i++){
//            categoryName.add(categoryDtoList.get(i).getTitle());
//        }
//        model.addAttribute("categoryName" ,categoryName);
        return "book/createbook";
    }

    @PostMapping("/add")
    public String getBookAdd(@ModelAttribute BookDto bookDto, RedirectAttributes redirectAttributes){
        try {
            //save into database
          BookDto bookDto1=  bookService.save(bookDto);
            System.out.println("save into database");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","book creation failed.");
            e.printStackTrace();
        }
        return "redirect:/book/home";
    }

}
