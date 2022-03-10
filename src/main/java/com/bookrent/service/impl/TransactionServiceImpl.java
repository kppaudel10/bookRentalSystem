package com.bookrent.service.impl;

import com.bookrent.dto.conversion.BookAndDto;
import com.bookrent.dto.conversion.MemberAndDto;
import com.bookrent.dto.transaction.TransactionDto;
import com.bookrent.entity.Book;
import com.bookrent.entity.Transaction;
import com.bookrent.enums.RentStatus;
import com.bookrent.repo.transaction.TransactionRepo;
import com.bookrent.service.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final MemberServiceImpl memberService;
    private final BookServiceImpl bookService;
    private final BookCodeServiceImpl bookCodeService;

    public TransactionServiceImpl(TransactionRepo transactionRepo, MemberServiceImpl memberService, BookServiceImpl bookService, BookCodeServiceImpl bookCodeService) {
        this.transactionRepo = transactionRepo;
        this.memberService = memberService;
        this.bookService = bookService;
        this.bookCodeService = bookCodeService;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) throws IOException, ParseException {
        Transaction transaction = Transaction.builder()
                .id(transactionDto.getId())
                .bookCode(transactionDto.getBookCode())
                .member(new MemberAndDto().getMember(
                        memberService.findById(transactionDto.getMemberId())))
                .fromDate(new Date())
                .noOfDays(transactionDto.getNoOfDays())
                .rentStatus(RentStatus.RENT)
                .book(new BookAndDto().getBook(
                        bookService.findById(transactionDto.getBookId()))).build();
        //save into database
        Transaction transaction1 = transactionRepo.save(transaction);
        //update stock value
        Book book= new BookAndDto().getBook(bookService.findById(transactionDto.getBookId()));
        bookService.updateBookStock(book.getId(),book.getStockCount()-1);

        //update bookCode rentStatus
        bookCodeService.updateRentStatusAsRent(transactionDto.getBookCode());

        return TransactionDto.builder().id(transaction.getId()).build();
    }

    @Override
    public List<TransactionDto> findAll() {
        return transactionRepo.findAll().stream().map(transaction -> {
            return TransactionDto.builder()
                    .id(transaction.getId())
                    .rentStatus(transaction.getRentStatus())
                    .memberId(transaction.getMember().getId())
                    .bookCode(transaction.getBookCode()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer integer) throws IOException, ParseException {
        Optional<Transaction> transaction = transactionRepo.findById(integer);
        Transaction transaction1;
        if (transaction.isPresent()) {
            transaction1 = transaction.get();
            return TransactionDto.builder()
                    .id(transaction1.getId())
                    .bookCode(transaction1.getBookCode())
                    .memberId(transaction1.getMember().getId())
                    .bookId(transaction1.getBook().getId())
                    .fromDate(transaction1.getFromDate())
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

    public List<Transaction> findAllTransaction() {
        return transactionRepo.findAll();
    }


    public void saveReturnTransaction(TransactionDto transactionDto) throws IOException, ParseException {

        transactionRepo.updateRentStatus(transactionDto.getId());
        //first update return date
        transactionRepo.updateReturnDate(new Date(),transactionDto.getId());
        //update rentStatus in bookCode table
       TransactionDto transactionDto1= findById(transactionDto.getId());
        try {
            bookService.updateBookStock(transactionDto1.getBookId()
                    , bookService.findById(transactionDto1.getBookId())
                            .getStockCount() +1 );
        } catch (IOException e) {
            e.printStackTrace();
        }


        bookCodeService.updateRentStatusAsReturn(transactionDto1.getBookCode());


    }

    public List<Transaction> findAllRent() {
        List<Transaction> transactionListOfRent = new ArrayList<>();
        List<Transaction> transactions = transactionRepo.findAll();
        for (Integer i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getRentStatus().ordinal() == RentStatus.RENT.ordinal()) {
                transactionListOfRent.add(transactions.get(i));
            }
        }
        return transactionListOfRent;
    }

    public Transaction findTransactionByBookCode(String bookCode){
        Transaction transaction = null;
       List<Transaction> transactionList = transactionRepo.findAll();
       for(Integer i =0;i<transactionList.size();i++){
           if(transactionList.get(i).getBookCode().equals(bookCode) && transactionList.get(i).getReturnDate() == null){
               transaction = transactionList.get(i);
           }
       }
       return transaction;
    }

    public List<Transaction> findAllReturnTransaction() {
        List<Transaction> transactionListOfReturn = new ArrayList<>();
        List<Transaction> transactions = transactionRepo.findAll();

        for (Integer i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getRentStatus().ordinal() == RentStatus.RETURN.ordinal()) {
                transactionListOfReturn.add(transactions.get(i));
            }
        }
        return transactionListOfReturn;
    }


}

