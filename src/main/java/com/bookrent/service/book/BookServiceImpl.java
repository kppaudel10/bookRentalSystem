package com.bookrent.service.book;

import com.bookrent.dto.book.BookDto;
import com.bookrent.service.GenericCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements GenericCrudService<BookDto, Integer> {
    @Override
    public BookDto save(BookDto bookDto) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void update(BookDto bookDto) {

    }

}
