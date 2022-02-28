package com.bookrent;

import com.bookrent.repo.author.AuthorRepo;
import com.bookrent.service.impl.AuthorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRentalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRentalSystemApplication.class, args);

    }

}
