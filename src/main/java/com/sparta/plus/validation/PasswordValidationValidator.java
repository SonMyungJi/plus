package com.sparta.plus.validation;

import com.sparta.plus.dto.SignupRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidationValidator implements ConstraintValidator<PasswordValidation, SignupRequestDto> {

    @Override
    public boolean isValid(SignupRequestDto signupRequestDto, ConstraintValidatorContext context) {
        String password = signupRequestDto.getPassword();
        String nickname = signupRequestDto.getNickname();

        // Check if password contains the nickname
        boolean isValid = !password.contains(nickname);

        // If not valid, set the error message
        if (!isValid) {
            String errorMessage = context.getDefaultConstraintMessageTemplate();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}