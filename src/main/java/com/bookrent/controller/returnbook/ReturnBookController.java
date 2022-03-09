package com.bookrent.controller.returnbook;

import com.bookrent.dto.transaction.TransactionDto;
import com.bookrent.service.impl.BookCodeServiceImpl;
import com.bookrent.service.impl.BookServiceImpl;
import com.bookrent.service.impl.MemberServiceImpl;
import com.bookrent.service.impl.TransactionServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/returnbook")
public class ReturnBookController {
    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;
    private final BookCodeServiceImpl bookCodeService;
    private final TransactionServiceImpl transactionService;

    public ReturnBookController(BookServiceImpl bookService,
                                MemberServiceImpl memberService,
                                BookCodeServiceImpl bookCodeService,
                                TransactionServiceImpl transactionService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.bookCodeService = bookCodeService;
        this.transactionService = transactionService;
    }

    @GetMapping("/home")
    public String getReturnBookHomePage(Model model){
        model.addAttribute("transactionDto",new TransactionDto());
        model.addAttribute("transactionList",
                transactionService.findAllReturnTransaction());
        return "transaction/returnbook/returnbookHomePage";
    }

    @GetMapping("/return")
    public String getRentPage(Model model){
        model.addAttribute("transactionDtoList",new TransactionDto());
        model.addAttribute("rentedCode",transactionService.findAllRent());
        model.addAttribute("bookList1", bookService.findAll());
        model.addAttribute("memberList1",memberService.findAll());
        model.addAttribute("bookCodeList1",bookCodeService.findAll());
        return "transaction/returnbook/returnBook";
    }

    @PostMapping("/returndata")
    public String showReturn(@ModelAttribute TransactionDto transactionDto, Model model) {

        model.addAttribute("transactionDetails",
                transactionService.findTransactionByBookCode(transactionDto.getBookCode()));
        return "transaction/returnbook/returnbookDetails";

    }
    @PostMapping("/add")
    public String getAddRentPage(@ModelAttribute TransactionDto transactionDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            transactionService.saveReturnTransaction(transactionDto);
            System.out.println("saved successfully");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Transaction unable to complete .");
            return "redirect:/returnbook/home";
        }
        return "redirect:/returnbook/home";
    }
}
