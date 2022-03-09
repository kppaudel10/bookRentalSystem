package com.bookrent.service.impl;

import com.bookrent.entity.BookCode;
import com.bookrent.enums.RentStatus;
import com.bookrent.repo.bookCode.BookCodeRepo;
import com.bookrent.service.bookCode.BookCodeService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookCodeServiceImpl implements BookCodeService {
   private final BookCodeRepo bookCodeRepo;

    public BookCodeServiceImpl(BookCodeRepo bookCodeRepo) {
        this.bookCodeRepo = bookCodeRepo;
    }

    @Override
    public BookCode save(BookCode bookCode) throws IOException, ParseException {
        bookCodeRepo.save(bookCode);
        return bookCode;
    }

    @Override
    public List<BookCode> findAll() {
      return bookCodeRepo.findAll();
    }

    @Override
    public BookCode findById(Integer integer) throws IOException {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        bookCodeRepo.deleteById(integer);
    }
    public void DeleteAllBookCode(Integer bookId){
        bookCodeRepo.deleteAllBookCode(bookId);
    }
    @Override
    public void update(BookCode bookCode) {

    }
    public Integer findIdByBookId(Integer bookId){
        return null;
    }

    public void updateRentStatusAsRent(String bookCode){
        Integer bookCodeId = findIdByBookCode(bookCode);
        bookCodeRepo.updateRentStatus(bookCodeId, RentStatus.RENT.ordinal());
    }

    public void updateRentStatusAsReturn(String bookCode){
        Integer bookCodeId = findIdByBookCode(bookCode);
        bookCodeRepo.updateRentStatus(bookCodeId,RentStatus.RETURN.ordinal());
    }

    public List<BookCode> findAllNotRentedBookCode(){
        List<BookCode> bookCodeList = bookCodeRepo.findAll();

        List<BookCode> notRentedBook = new ArrayList<>();

        for (Integer i =0;i<bookCodeList.size();i++){
            if (bookCodeList.get(i).getRentStatus().equals(RentStatus.RENT)){

            }else {
                notRentedBook.add(bookCodeList.get(i));
            }
        }

        return notRentedBook;
    }

    public Integer findIdByBookCode(String bookCode){
      List<BookCode> bookCodeList =   bookCodeRepo.findAll();
      Integer bookCodeId= null;
      for (Integer i =0;i<bookCodeList.size();i++){
          if (bookCodeList.get(i).getBookCode().equals(bookCode)){
              bookCodeId = bookCodeList.get(i).getId();
          }
      }
        return bookCodeId;
    }
}
