package io.spring.vikop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDetailsDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @URL
    private String avatarUrl;

}
