package com.bookrent.dto.member;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Integer id;
    //    @NotNull
//    @NotEmpty(message = "name cannot be empty")
    private String name;

    //    @NotEmpty(message = "email cannot be empty")
    private String email;

    //    @NotEmpty(message = "contact cannot be empty")
    private String contact;

    //    @NotEmpty(message = "address cannot be empty")
    private String address;

}
