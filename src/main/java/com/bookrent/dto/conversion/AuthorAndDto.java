package com.bookrent.dto.conversion;

import com.bookrent.dto.author.AuthorDto;
import com.bookrent.entity.Author;

public class AuthorAndDto {
    public Author getAuthor(AuthorDto authorDto){
//        return Author.builder()
//                .id(authorDto.getId())
//                .name(authorDto.getName())
//                .email(authorDto.getEmail())
//                .mobile_number(authorDto.getMobile_number()).build();
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobile_number(author.getMobile_number());
        return author;
    }
}
