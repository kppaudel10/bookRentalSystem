package com.bookrent.controller.member;//package com.bookrent.controller;
//
//import com.bookrent.dto.MemberDto;
//import com.bookrent.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("member")
//public class MemberController {
//    @Autowired
//    private MemberService memberService;
//
//    @GetMapping
//    public String memeberHomePage() {
//        return "member/memberPage";
//    }
//
//    @GetMapping("/add")
//    public String getMemberAddPage(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes){
//        return "create/createmember";
//    }
//}