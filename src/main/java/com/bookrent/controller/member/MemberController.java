package com.bookrent.controller.member;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

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
    public String getMemberAddPage(@Valid @ModelAttribute("memberDto") MemberDto memberDto,
                                   BindingResult bindingResult,Model model) {
        MemberDto memberDto1 = null;
        if (!bindingResult.hasErrors()){
            try {
            memberDto1 = memberServiceimpl.save(memberDto);
            }catch (Exception e){
                model.addAttribute("message","member creation failed.");
//            return "redirect:/member/home";
            }
            if (memberDto1 != null){
               model.addAttribute("message","member created successfully.");
            }
        }
        model.addAttribute("memberDto",memberDto);
//        return "member/createmember";
        return "member/createmember";
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
    public String getDelete(@PathVariable Integer id) throws IOException {
        memberServiceimpl.deleteById(id);
        return "redirect:/member/home";
    }
}