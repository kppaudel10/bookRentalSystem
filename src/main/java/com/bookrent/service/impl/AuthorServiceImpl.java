package com.bookrent.service.impl;

import com.bookrent.dto.author.AuthorDto;
import com.bookrent.entity.Author;
import com.bookrent.repo.author.AuthorRepo;
import com.bookrent.service.author.AuthorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    //get author repo to work with database
    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        //create author
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobile_number(authorDto.getMobile_number());
        //save into database
        Author author1 = authorRepo.save(author);
        return authorDto;
    }

    @Override
    public List<AuthorDto> findAll() {
        //find all author from database
        return authorRepo.findAll(Sort.by(Sort.Direction.ASC,"id")).stream().map(author -> {
            return AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .email(author.getEmail())
                    .mobile_number(author.getMobile_number())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Integer integer) {
        Optional<Author> authors = authorRepo.findById(integer);
        Author author;
        if (authors.isPresent()) {
            author = authors.get();
            return AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .email(author.getEmail())
                    .mobile_number(author.getMobile_number()).build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        authorRepo.deleteById(integer);
    }

    @Override
    public void update(AuthorDto authorDto) {
        Integer id = authorDto.getId();
        authorRepo.updateName(authorDto.getName(),id);
        authorRepo.updateEmail(authorDto.getEmail(),id);
        authorRepo.updateMobile(authorDto.getMobile_number(),id);
    }

}
