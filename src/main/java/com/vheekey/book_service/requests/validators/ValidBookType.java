package com.vheekey.book_service.requests.validators;

import com.vheekey.book_service.enums.BookType;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBookType.Validator.class)
@Documented
public @interface ValidBookType {
    String message() default "Invalid book type. Valid book types are: {validTypes}";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    class Validator implements ConstraintValidator<ValidBookType, BookType> {
        @Override
        public boolean isValid(BookType value, ConstraintValidatorContext context) {
            return value != null;
        }
    }
}
