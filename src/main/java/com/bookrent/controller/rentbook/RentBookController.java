package com.bookrent.controller.rentbook;

import com.bookrent.dto.transaction.TransactionDto;
import com.bookrent.service.impl.BookCodeServiceImpl;
import com.bookrent.service.impl.BookServiceImpl;
import com.bookrent.service.impl.MemberServiceImpl;
import com.bookrent.service.impl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/rentbook")
public class RentBookController {
    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;
    private final BookCodeServiceImpl bookCodeService;
    private final TransactionServiceImpl transactionService;

    public RentBookController(BookServiceImpl bookService, MemberServiceImpl memberService, BookCodeServiceImpl bookCodeService, TransactionServiceImpl transactionService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.bookCodeService = bookCodeService;
        this.transactionService = transactionService;
    }

    @GetMapping("/home")
    public String getRentBookHomePage(Model model){
        model.addAttribute("transactionDto",new TransactionDto());
        model.addAttribute("transactionList",transactionService.findAll());
        return "transaction/rentbook/rentbookHomePage";
    }

    @GetMapping("/rent")
    public String getRentPage(Model model){
        model.addAttribute("transactionDto",new TransactionDto());
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("memberList",memberService.findAll());
        model.addAttribute("bookCodeList",bookCodeService.findAll());
        return "transaction/rentbook/rentbook";
    }
    @PostMapping("/add")
    public String getAddRentPage(@ModelAttribute TransactionDto transactionDto,
                                 RedirectAttributes redirectAttributes){
        try {
           TransactionDto transactionDto1 = transactionService.save(transactionDto);
            System.out.println("saved successfully");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Transaction unable to complete .");
            return "redirect:/rentbook/home";
        }
        return "redirect:/rentbook/home";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(@PathVariable Integer id, Model model) throws IOException, ParseException {
        model.addAttribute("transactionList",transactionService.findById(id));
        try {
            model.addAttribute("memberDetails",memberService.findById
                    (transactionService.findById(id).getMember().getId()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("bookDetails",
                bookService.findById(transactionService.findById(id).getBook().getId()));
        return "transaction/rentbook/viewrentDetails";
    }
}
