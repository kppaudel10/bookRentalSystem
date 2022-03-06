package com.bookrent.service.impl;

import com.bookrent.component.FileStorageComponent;
import com.bookrent.dto.book.BookDto;
import com.bookrent.dto.response.ResponseDto;
import com.bookrent.entity.Book;
import com.bookrent.repo.book.BookRepo;
import com.bookrent.service.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements GenericCrudService<BookDto, Integer> {
    private final BookRepo bookRepo;
    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;
    private final FileStorageComponent fileStorageComponent;
    private final BookCodeServiceImpl bookCodeService;

    public BookServiceImpl(BookRepo bookRepo, CategoryServiceImpl categoryService, AuthorServiceImpl authorService, FileStorageComponent fileStorageComponent, BookCodeServiceImpl bookCodeService) {
        this.bookRepo = bookRepo;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.fileStorageComponent = fileStorageComponent;
        this.bookCodeService = bookCodeService;
    }

    @Override
    public BookDto save(BookDto bookDto) throws IOException, ParseException {
        ResponseDto responseDto = FileStorageComponent.storeFile(bookDto.getMultipartFile());
        if (responseDto.isStatus()) {
            Book book = null;
            book = Book.builder()
                    .id(bookDto.getId())
                    .name(bookDto.getName())
                    .isbn(bookDto.getIsbn())
                    .numberOfPage(bookDto.getNumberOfPage())
                    .rating(bookDto.getRating())
                    .stockCount(bookDto.getStockCount())
                    .published_date(new SimpleDateFormat("dd-MM-yyyy").parse(bookDto.getPublished_date()))
                    .coverPhotoPath(responseDto.getMessage())
                    .authorSet(bookDto.getAuthorList())
                    .bookCode(bookDto.getBookCode())
                    .category(bookDto.getCategory())
                    .build();

            book = bookRepo.save(book);
            return BookDto.builder().id((book.getId())).build();
        } else {
            log.error(responseDto.getMessage());
            return null;
        }
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepo.findAll().stream().map(book -> {
            try {
                return BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .isbn(book.getIsbn())
                        .numberOfPage(book.getNumberOfPage())
                        .stockCount(book.getStockCount())
                        .rating(book.getRating())
                        .published_date(new SimpleDateFormat("dd-MM-yyyy").format(book.getPublished_date()))
                        .coverPhotoPath(fileStorageComponent.base64Encoded(book.getCoverPhotoPath()))
                        .category(book.getCategory())
                        .bookCode(book.getBookCode())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer integer) throws IOException {
        Optional<Book> byId = bookRepo.findById(integer);
        Book book = null;
        if (byId.isPresent()) {
            book = byId.get();
            return BookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .isbn(book.getIsbn())
                    .numberOfPage(book.getNumberOfPage())
                    .stockCount(book.getStockCount())
                    .rating(book.getRating())
                    .published_date(new SimpleDateFormat("yyyy-MM-dd").format(book.getPublished_date()))
                    .coverPhotoPath(fileStorageComponent.base64Encoded(book.getCoverPhotoPath()))
                    .authorList(book.getAuthorSet())
                    .bookCode(book.getBookCode())
                    .category(book.getCategory()).build();

        }
        return BookDto.builder().id(book.getId()).build();
    }

    @Override
    public void deleteById(Integer integer) throws IOException {
        //find by id
       BookDto bookDto = findById(integer);
        System.out.println(bookDto.getId());
       bookCodeService.DeleteAllBookCode(bookDto.getId());
        bookRepo.deleteById(integer);
    }

    @Override
    public void update(BookDto bookDto) {

    }

}
