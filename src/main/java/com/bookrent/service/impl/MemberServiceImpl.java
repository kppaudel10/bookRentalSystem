package com.bookrent.service.impl;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.entity.Member;
import com.bookrent.repo.member.MemberRepo;
import com.bookrent.service.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = Member.builder()
                        .id(memberDto.getId())
                .full_name(memberDto.getName())
                .email(memberDto.getEmail())
                .mobile_no(memberDto.getContact())
                .address(memberDto.getAddress()).build();
        //save the member entity
        Member save = memberRepo.save(member);
        return MemberDto.builder().id(save.getId()).build();
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepo.findAll().stream().map(member -> {
            return MemberDto.builder()
                    .id(member.getId())
                    .address(member.getAddress())
                    .contact(member.getMobile_no())
                    .name(member.getFull_name())
                    .email(member.getEmail()).build();
        }).collect(Collectors.toList());
    }

    @Override
    public MemberDto findById(Integer integer) {
        Optional<Member> findId = memberRepo.findById(integer);
        Member member;
        if (findId.isPresent()){
            member = findId.get();
            return MemberDto.builder()
                    .id(member.getId())
                    .name(member.getFull_name())
                    .contact(member.getMobile_no())
                    .email(member.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        memberRepo.deleteById(integer);
    }

    @Override
    public void update(MemberDto memberDto) {

    }




}
