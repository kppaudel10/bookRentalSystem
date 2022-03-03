package com.bookrent.controller.member;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/update/{id}")
    public String getAuthorUpdatePage(@PathVariable Integer id, Model model){
        model.addAttribute("memberDto",memberServiceimpl.findById(id));
        return "member/updatemember";
    }

    @PostMapping("/update/{id}")
    public String getUpdate(@PathVariable Integer id , @ModelAttribute("memberDto")
            MemberDto memberDto ,RedirectAttributes redirectAttributes){
        try {
            MemberDto oldMemberDto = memberServiceimpl.findById(id);
            oldMemberDto.setName(memberDto.getName());
            oldMemberDto.setEmail(memberDto.getEmail());
            oldMemberDto.setAddress(memberDto.getAddress());
            oldMemberDto.setContact(memberDto.getContact());
            memberServiceimpl.update(oldMemberDto);


        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "author creation failed.");
            e.printStackTrace();
        }
        return "redirect:/member/home";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable Integer id){
        memberServiceimpl.deleteById(id);
        return "redirect:/member/home";
    }
}