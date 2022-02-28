package com.bookrent.service.author;

import com.bookrent.dto.author.AuthorDto;
import com.bookrent.repo.author.AuthorRepo;
import com.bookrent.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService extends GenericCrudService<AuthorDto , Integer> {
}
