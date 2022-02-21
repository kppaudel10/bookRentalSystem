package com.bookrent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String mobileNumber;
    private String filePath;
    private MultipartFile multipartFile;
}
