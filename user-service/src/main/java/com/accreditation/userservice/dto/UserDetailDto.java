package com.accreditation.userservice.dto;

import com.accreditation.userservice.util.ExceptionConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailDto {

    @NotBlank(message = ExceptionConstant.USER_NAME_IS_MANDATORY)
    private String username;

    /*should contain @ and .com imp */
    @NotBlank(message = ExceptionConstant.EMAIL_ID_IS_MANDATORY)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE) // re-verify-regex
    private String email;

    @NotBlank(message = ExceptionConstant.PASSWORD_IS_MANDATORY)
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = ExceptionConstant.PASSWORD_SHOULD_BE_ALPHANUMERIC) // alphanumeric
    @Size(min=8,message = ExceptionConstant.PASSWORD_MINIMUM_LENGTH_ERROR)
    private String password;


    private String role;

}