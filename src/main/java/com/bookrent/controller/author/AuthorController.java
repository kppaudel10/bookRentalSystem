package com.bookrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author")
public class AuthorController {

    @GetMapping()
    public String getAuthorPage(){
        return "author/authorPage";
    }

    @GetMapping("/signup")
    public String getAuthorSingUpPage(){
        return "create/createauthor";
    }
}
