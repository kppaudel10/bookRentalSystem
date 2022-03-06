package com.bookrent.service.impl;

import com.bookrent.dto.conversion.BookAndDto;
import com.bookrent.dto.conversion.MemberAndDto;
import com.bookrent.dto.transaction.TransactionDto;
import com.bookrent.entity.Transaction;
import com.bookrent.enums.RentStatus;
import com.bookrent.repo.transaction.TransactionRepo;
import com.bookrent.service.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final MemberServiceImpl memberService;
    private final BookServiceImpl bookService;

    public TransactionServiceImpl(TransactionRepo transactionRepo, MemberServiceImpl memberService, BookServiceImpl bookService) {
        this.transactionRepo = transactionRepo;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) throws IOException, ParseException {
        Transaction transaction = Transaction.builder()
                .id(transactionDto.getId())
                .code(transactionDto.getBookCode())
                .member(transactionDto.getMember())
                .fromDate(new Date())
                .noOfDays(transactionDto.getNoOfDays())
                .rentStatus(RentStatus.RENT)
                .book(transactionDto.getBook()).build();
        //save into database
     Transaction transaction1=   transactionRepo.save(transaction);
        return TransactionDto.builder().id(transaction.getId()).build();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepo.findAll().stream().map(transaction -> {
            return TransactionDto.builder()
                    .id(transaction.getId())
                    .member(new MemberAndDto().getMember(memberService.findById(transaction.getMember().getId())))
                    .bookCode(transaction.getCode()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer integer) throws IOException, ParseException {
       Optional<Transaction> transaction = transactionRepo.findById(integer);
       Transaction transaction1;
       if (transaction.isPresent()){
           transaction1 = transaction.get();
           return TransactionDto.builder()
                   .id(transaction1.getId())
                   .bookCode(transaction1.getCode())
                   .member(new MemberAndDto().getMember(memberService.findById(transaction1.getMember().getId())))
                   .book(new BookAndDto().getBook(bookService.findById(transaction1.getBook().getId())))
                   .noOfDays(transaction1.getNoOfDays()).build();

       }
       return null;
    }

    @Override
    public void deleteById(Integer integer) throws IOException {

    }

    @Override
    public void update(TransactionDto transactionDto) {

    }
    public List<Transaction> findAllTransaction(){
        return transactionRepo.findAll();
    }
}
