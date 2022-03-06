package com.bookrent.dto.conversion;

import com.bookrent.dto.member.MemberDto;
import com.bookrent.entity.Member;

public class MemberAndDto {
    public Member getMember(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .full_name(memberDto.getName())
                .address(memberDto.getAddress())
                .mobile_no(memberDto.getContact())
                .email(memberDto.getEmail()).build();
    }
}
