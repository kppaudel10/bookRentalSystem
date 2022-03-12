package com.bookrent.service.impl;

import com.bookrent.component.MailSendComponent;
import com.bookrent.dto.author.AuthorDto;
import com.bookrent.entity.Author;
import com.bookrent.enums.ActiveClosed;
import com.bookrent.repo.author.AuthorRepo;
import com.bookrent.service.author.AuthorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    //get author repo to work with database
    private final AuthorRepo authorRepo;
    private final MailSendComponent mailSend;
    private Integer exitAuthorId;

    public AuthorServiceImpl(AuthorRepo authorRepo, MailSendComponent mailSend) {
        this.authorRepo = authorRepo;
        this.mailSend = mailSend;
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        //create author
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setMobile_number(authorDto.getMobile_number());
        author.setActiveClosed(ActiveClosed.ACTIVE);
        //save into database
        //if author is already exit but status is closed
        if (isAlreadyExit(author.getEmail(), author.getMobile_number())){
            authorRepo.updateName(author.getName(),exitAuthorId);
            authorRepo.updateAuthorStatus(exitAuthorId,ActiveClosed.ACTIVE.ordinal());
        }else {
            Author author1 = authorRepo.save(author);
            mailSend.sendMail(authorDto);
        }
        return authorDto;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authorList = authorRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<AuthorDto> activeAuthorList = new ArrayList<>();
        for (Author author : authorList){
            if (author.getActiveClosed().equals(ActiveClosed.ACTIVE)){
                AuthorDto authorDto = new AuthorDto();
                authorDto.setId(author.getId());
                authorDto.setName(author.getName());
                authorDto.setMobile_number(author.getMobile_number());
                authorDto.setEmail(author.getEmail());
                activeAuthorList.add(authorDto);
            }
        }
        return activeAuthorList;
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
//        authorRepo.deleteById(integer);
        //perform soft delete
        authorRepo.updateAuthorStatus(integer,ActiveClosed.CLOSED.ordinal());
    }

    @Override
    public void update(AuthorDto authorDto) {
        String AuthorOldEmail = authorRepo.findById(authorDto.getId()).get().getEmail();
        Integer id = authorDto.getId();
        authorRepo.updateName(authorDto.getName(), id);
        authorRepo.updateEmail(authorDto.getEmail(), id);
        authorRepo.updateMobile(authorDto.getMobile_number(), id);

        if (!(authorRepo.findById(authorDto.getId()).get()
                .getEmail().equals(AuthorOldEmail))){
            mailSend.sendMail(authorDto);
        }
    }

    private boolean isAlreadyExit(String email,String number){
        boolean alreadyExit = false;
      List<Author> authorList=  authorRepo.findAll();
      for (Author author : authorList){
          if (author.getEmail().equals(email) && author.getMobile_number().equals(number)){
              alreadyExit = true;
              exitAuthorId = author.getId();
              break;
          }
      }
        return alreadyExit;
    }

}
