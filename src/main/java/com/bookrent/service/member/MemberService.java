package com.bookrent.service.member;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends GenericCrudService<MemberDto, Integer> {
}