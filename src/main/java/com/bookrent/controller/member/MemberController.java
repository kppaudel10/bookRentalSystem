package com.bookrent.controller.member;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberServiceimpl;

    public MemberController(MemberServiceImpl memberServiceimpl) {
        this.memberServiceimpl = memberServiceimpl;
    }


    @GetMapping("/home")
    public String memeberHomePage(Model model) {
        model.addAttribute("memberDto" ,new MemberDto());
        model.addAttribute("data",memberServiceimpl.findAll());
        return "member/memberHomePage";
    }
    @GetMapping("/memberForm")
    public String getMemberForm(Model model){
        model.addAttribute("memberDto" ,new MemberDto());
        return "member/createmember";
    }
    @PostMapping ("/add")
    public String getMemberAddPage(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        try {
            memberDto = memberServiceimpl.save(memberDto);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","member creation failed.");
            return "redirect:/member/home";
        }
        if (memberDto != null){
            redirectAttributes.addFlashAttribute("message","member created");
        }
        return "redirect:/member/home";
    }

    @GetMapping("/update")
    public String getAuthorUpdatePage(Model model){
        model.addAttribute("updateDto",memberServiceimpl.findById(205));
        return "member/updatemember";
    }

    @PostMapping("/update")
    public String getUpdate(@ModelAttribute MemberDto memberDto , RedirectAttributes redirectAttributes){
        try {
            //delete old data from datbase
//            authorService.deleteById(authorDto.getId());
            //then save into database
            memberServiceimpl.update(memberDto);
            System.out.println("save into database");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","author creation failed.");
            e.printStackTrace();
        }
        return "redirect:/member/home";
    }
}