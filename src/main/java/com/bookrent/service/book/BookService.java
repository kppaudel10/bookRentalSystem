package com.bookrent.service.book;

import com.bookrent.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookService extends JpaRepository<Book,Integer> {
}
