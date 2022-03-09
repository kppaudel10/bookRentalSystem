package com.bookrent.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class AuthorDto {
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Please enter valid email address")
    private String email;

    @NotEmpty(message = "Mobile number must not be empty")
    @Size(max = 10,min = 10 ,message = "Not valid length of number")
    private String mobile_number;
}
