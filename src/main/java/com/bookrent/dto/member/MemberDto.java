package com.bookrent.dto.member;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "Enter valid email address")
    private String email;

    @NotEmpty(message = "contact cannot be empty")
    @Pattern(regexp = "^\\+?(?:977)?[ -]?(?:(?:(?:98|97)-?\\d{8})|(?:01-?\\d{7}))$",
            message = "Invalid number")
    private String contact;

    @NotEmpty(message = "address cannot be empty")
    private String address;

}
