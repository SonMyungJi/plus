package com.sparta.plus.validation;

import com.sparta.plus.dto.SignupRequestDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupRequestDto> {

    @Override
    public boolean isValid(SignupRequestDto signupRequestDto, ConstraintValidatorContext context) {
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getConfirmPassword())) {
            String errorMessage = context.getDefaultConstraintMessageTemplate();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
