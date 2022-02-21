package com.bookrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rentbook")
public class RentBookController {

    @GetMapping()
    public String getRentBookHomePage(){
        return "transaction/rentbook/rentbookPage";
    }
}
