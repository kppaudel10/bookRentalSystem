package com.bookrent.service.impl;

import com.bookrent.entity.BookCode;
import com.bookrent.repo.bookCode.BookCodeRepo;
import com.bookrent.service.bookCode.BookCodeService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
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
    public BookCode findByBookId(Integer bookId){
        return null;
    }
}
