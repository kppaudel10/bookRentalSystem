//package com.bookrent.service.impl;
//
//import com.bookrent.component.FileStorageComponent;
//import com.bookrent.dto.MemberDto;
//import com.bookrent.dto.ResponseDto;
//import com.bookrent.entity.Member;
//import com.bookrent.repo.MemberRepo;
//import com.bookrent.service.GenericCrudService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//@Slf4j
//@Service
//public class MemberServiceImpl implements  GenericCrudService<MemberDto,Integer> {
//
//    private final MemberRepo memberRepo;
//    private final FileStorageComponent fileStorageComponent;
//    public MemberServiceImpl(MemberRepo memberRepo, FileStorageComponent fileStorageComponent) {
//        this.memberRepo = memberRepo;
//        this.fileStorageComponent = fileStorageComponent;
//    }
//
//    @Override
//    public MemberDto save(MemberDto memberDto) throws IOException {
//        MemberDto entity = null;
//        ResponseDto responseDto = fileStorageComponent.storeFile(memberDto.getMultipartFile());
//        entity = MemberDto.builder().id(memberDto.getId())
//                        .name(memberDto.getName())
//                                .email(memberDto.getEmail())
//                                        .address((memberDto.getAddress()))
//                .filePath(String.valueOf(fileStorageComponent))
//                                                .mobileNumber(memberDto.getMobileNumber()).build();
//        memberRepo.save(entity);
//        return null;
//    }
//
//    @Override
//    public List<MemberDto> findALl() {
//        return null;
//    }
//
//    @Override
//    public MemberDto findDyId(Integer integer) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//
//    }
//}
