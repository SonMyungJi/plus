package com.sparta.plus.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "비밀번호에 닉네임과 같은 값이 들어가면 안됩니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
