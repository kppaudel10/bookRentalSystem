package com.bookrent.controller.author;

import com.bookrent.dto.author.AuthorDto;
import com.bookrent.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/home")
    public String getAuthorHomePage(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        model.addAttribute("data", authorService.findAll());
        return "author/authorPage";
    }

    @GetMapping("/signup")
    public String getAuthorSingUpPage(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        return "author/createauthor";
    }

    @PostMapping("/add")
    public String getAuthorAddPage(@Valid @ModelAttribute("authorDto") AuthorDto authorDto,
                                   BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                //save into database
                authorDto = authorService.save(authorDto);
//            System.out.println("save into database");
                model.addAttribute("message", "Author successfully added.");
            } catch (Exception e) {
                model.addAttribute("message", "Failed to create Author.");
                e.printStackTrace();
            }
        }
        model.addAttribute("authorDto",authorDto);
//        return "redirect:/author/signup";
        return "author/createauthor";
    }


    @GetMapping("/update/{id}")
    public String getAuthorUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("authorDto", authorService.findById(id));
//        AuthorDto authorDto = authorService.findById(id);
//        System.out.println(authorDto.getName());.
        return "author/updateauthor";
    }

    @PostMapping("/update/{id}")
    public String getUpdate(@PathVariable Integer id, @ModelAttribute("authorDto")
            AuthorDto newAuthorDto
            , RedirectAttributes redirectAttributes) {
        try {

            AuthorDto oldAuthor = authorService.findById(id);
            oldAuthor.setName(newAuthorDto.getName());
            oldAuthor.setEmail(newAuthorDto.getEmail());
            oldAuthor.setMobile_number(newAuthorDto.getMobile_number());
            //send for update
            authorService.update(oldAuthor);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "author creation failed.");
            e.printStackTrace();
        }

        return "redirect:/author/home";
    }

    @GetMapping("/delete/{id}")
    public String getDeletePage(@PathVariable Integer id) {
        authorService.deleteById(id);
        System.out.println("delete successfully");
        return "redirect:/author/home";
    }
}
