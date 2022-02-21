package com.bookrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("book")
public class BookController {

    @GetMapping()
    public String getBookHomePage(){
        return "book/bookPage";
    }

    @GetMapping("/add")
    public String getBookAddPage(){
        return "create/createbook";
    }
}
