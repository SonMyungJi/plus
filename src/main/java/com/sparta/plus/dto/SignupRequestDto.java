package com.sparta.plus.dto;

import com.sparta.plus.validation.PasswordMatches;
import com.sparta.plus.validation.PasswordValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
@PasswordValidation
public class SignupRequestDto {

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{3,}$")
    private String nickname;

    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String confirmPassword;
}
