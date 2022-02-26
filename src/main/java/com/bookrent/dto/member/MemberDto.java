package com.bookrent.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Integer id;
    @NotNull
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @NotEmpty(message = "contact cannot be empty")
    private String contact;
}
