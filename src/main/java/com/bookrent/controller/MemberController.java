package com.bookrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

    @GetMapping
    public String memeberHomePage() {
        return "member/memberPage";
    }

    @GetMapping("/add")
    public String getMemberAddPage(){
        return "create/createmember";
    }
}