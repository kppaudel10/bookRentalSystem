package com.bookrent.controller.returnbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("returnbook")
public class ReturnBookController {

    @GetMapping()
    public String getReturnBookHomePage(){
        return "transaction/returnbook/returnbookPage";
    }
}
