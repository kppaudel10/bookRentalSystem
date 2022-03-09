package com.bookrent.controller.book;

import com.bookrent.dto.book.BookDto;
import com.bookrent.entity.BookCode;
import com.bookrent.enums.RentStatus;
import com.bookrent.service.bookCode.BookCodeService;
import com.bookrent.service.impl.AuthorServiceImpl;
import com.bookrent.service.impl.BookServiceImpl;
import com.bookrent.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;
    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;
    private final BookCodeService bookCodeService;

    public BookController(BookServiceImpl bookService, CategoryServiceImpl categoryService, AuthorServiceImpl authorService, BookCodeService bookCodeService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookCodeService = bookCodeService;
    }

    @GetMapping("/home")
    public String getBookHomePage(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("data", bookService.findAll());
        return "book/bookPage";
    }

    @GetMapping("/add")
    public String getBookAddPage(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("authorList", authorService.findAll());
        return "book/createbook";
    }

    @PostMapping("/add")
    public String getBookAdd(@Valid @ModelAttribute("bookDto") BookDto bookDto
                             , BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("message","Failed to add book.");
        }else {
            try {
                //save into database
                bookDto = bookService.save(bookDto);
                if (bookDto != null) {
                    model.addAttribute("message", "book added successfully");
                    //if save into database then generate code
                    Integer stockAvailable= bookDto.getStockCount();
                    for (Integer i =1;i<=stockAvailable;i++){
                        //generate book code and store into database
                        BookCode bookCode = new BookCode();
                        String bookCodeG = bookDto.getBookCode() + i;
                        bookCode.setBookId(bookDto.getId());
                        bookCode.setBookCode(bookCodeG);
                        bookCode.setRentStatus(RentStatus.RETURN);
                        bookCodeService.save(bookCode);
                    }

                } else {
                    model.addAttribute("message","Failed to add book.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        return "redirect:/book/home";
        return "book/createbook";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(@PathVariable Integer id, Model model) throws IOException {
        System.out.println(bookService.findById(id).getCategory().getId());
        model.addAttribute("bookDetail", bookService.findById(id));
        model.addAttribute("categoryData", categoryService.findById(bookService.findById(id).getCategory().getId()));
        model.addAttribute("authorData", bookService.findById(id).getAuthorList());
        model.addAttribute("coverPhotoPath",bookService.findById(id).getCoverPhotoPath());
        return "book/viewBookDetails";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable Integer id) throws IOException {
        bookService.deleteById(id);
        return "redirect:/book/home";
    }
}
