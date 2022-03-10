package com.bookrent.dto.author;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Setter
@Getter
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
    @Pattern(regexp = "^\\+?(?:977)?[ -]?(?:(?:(?:98|97)-?\\d{8})|(?:01-?\\d{7}))$",
            message = "Invalid number.")
    @Size(max = 10,min = 10 ,message = "Not valid length of number")
    private String mobile_number;
}
